package com.example.tugasakhirnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity


class MainActivity : AppCompatActivity() {
    private lateinit var card1 : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        card1 = findViewById<CardView>(R.id.myProfile)
        card1.setOnClickListener{
            startActivity(Intent(this,ProfileActivity::class.java))


        }
    }
}