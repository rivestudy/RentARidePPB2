package com.dkp.rentarideppb2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class homeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sewaButton: Button = findViewById(R.id.sewaButton)
        sewaButton.setOnClickListener {
            val intent = Intent(this@homeActivity, SewaActivity::class.java)
            startActivity(intent)
        }
        val tambahsewaButton: Button = findViewById(R.id.tambahsewaButton)
        tambahsewaButton.setOnClickListener {
            val intent = Intent(this@homeActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val riwayatsewaButton: Button = findViewById(R.id.riwayatsewaButton)
        riwayatsewaButton.setOnClickListener {
            val intent = Intent(this@homeActivity, SewaActivity::class.java)
            startActivity(intent)
        }


    }

}