package com.example.tugasakhirnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.example.tugasakhirnew.fragment.VPAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val getID = intent
        val id = getID.getStringExtra("idNFC")

        tvNameID.setText(id)



        myProfile.setOnClickListener{
            val idNFC = Intent(this@HomeActivity, ProfileActivity::class.java)
            idNFC.putExtra("idNFC2", getID.toString())
            startActivity(idNFC)

            startActivity(Intent(this,ProfileActivity::class.java))


        }

    }
}