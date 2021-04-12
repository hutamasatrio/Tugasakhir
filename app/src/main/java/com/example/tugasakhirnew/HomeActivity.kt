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

        myProfile.setOnClickListener{
            startActivity(Intent(this,ProfileActivity::class.java))


        }

    }
}