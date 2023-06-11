package com.dkp.rentarideppb2

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView

import com.dkp.rentarideppb2.adapter.UserAdapter
import com.dkp.rentarideppb2.data.AppDatabase
import com.dkp.rentarideppb2.data.entity.User
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler)
        fab = findViewById(R.id.fab)

        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{


            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(list[position].namamobil)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener
                {dialog, which ->
                    if (which==0){
                        val intent = Intent(this@MainActivity, AddDataActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    } else if (which==1){
                        database.userDao().delete(list[position])
                        getData()
                    } else{
                        dialog.dismiss()
                    }
                }
                )
                val dialogView = dialog.create()
                dialogView.show()
                }

            }
        )

        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        //adapter.setDialog(object : UserAdapter.Dialog {
        //    override fun onClick(position: Int) {
         //       val dialog = AlertDialog.Builder((this@MainActivity))
          //      dialog.setTitle(list[position].namamobil)
            //    dialog.setItems(R.array.items_option, DialogInterface.OnClickListener { dialog, which ->
              //      if (which == 0) {
                //        val intent = Intent(this@MainActivity, AddDataActivity::class.java)
                 //       intent.putExtra("id", list[position].uid)
         //               startActivity(intent)
           //         }
           //     })
           // }

        //})


       // recyclerView.adapter = adapter
       // recyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        //recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))

        fab.setOnClickListener {
            startActivity(Intent(this, AddDataActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData() {
        list.clear()
        list.addAll(database.userDao().getAll())
        adapter.notifyDataSetChanged()
    }
}
