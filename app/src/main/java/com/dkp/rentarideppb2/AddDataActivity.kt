package com.dkp.rentarideppb2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dkp.rentarideppb2.data.AppDatabase
import com.dkp.rentarideppb2.data.entity.User

class AddDataActivity : AppCompatActivity() {
    private lateinit var namamobil: EditText
    private lateinit var merkmobil: EditText
    private lateinit var hargamobil: EditText
    private lateinit var tambahbutton: Button
    private lateinit var database: AppDatabase


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        namamobil = findViewById(R.id.namamobil)
        merkmobil = findViewById(R.id.merkmobil)
        hargamobil = findViewById(R.id.hargamobil)
        tambahbutton = findViewById(R.id.tambahbutton)

        database = AppDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if (intent!=null){
            val id = intent.getInt("id", 0)
            val user = database.userDao().get(id)

            namamobil.setText(user.namamobil)
            merkmobil.setText(user.merkmobil)
            hargamobil.setText(user.hargamobil)
        }

        namamobil.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                namamobil.hint = ""
            }
        }

        merkmobil.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                merkmobil.hint = ""
            }
        }

        hargamobil.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                hargamobil.hint = "Rp. "
            } else {
                hargamobil.hint = ""
            }
        }

        tambahbutton.setOnClickListener {
            if (namamobil.text.isNotEmpty() && merkmobil.text.isNotEmpty() && hargamobil.text.isNotEmpty()) {
                if(intent!= null){
                    database.userDao().update(
                        User(
                            intent.getInt("id",0),
                            namamobil.text.toString(),
                            hargamobil.text.toString(),
                            merkmobil.text.toString()
                        )
                    )
                } else {
                    database.userDao().insertAll(
                        User(
                            null,
                            namamobil.text.toString(),
                            hargamobil.text.toString(),
                            merkmobil.text.toString()
                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Isi yang lengkap", Toast.LENGTH_SHORT).show()
            }
        }
    }
}