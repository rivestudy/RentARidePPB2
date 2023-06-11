package com.dkp.rentarideppb2.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dkp.rentarideppb2.R
import com.dkp.rentarideppb2.data.entity.User

class UserAdapter(var list: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var namamobil: TextView
        var merkmobil: TextView
        var hargamobil: TextView


        init {
            namamobil = view.findViewById(R.id.namamobil)
            merkmobil = view.findViewById(R.id.merkmobil)
            hargamobil = view.findViewById(R.id.hargamobil)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namamobil.text = list[position].namamobil
        holder.merkmobil.text = list[position].merkmobil
        holder.hargamobil.text = list[position].hargamobil
    }

}