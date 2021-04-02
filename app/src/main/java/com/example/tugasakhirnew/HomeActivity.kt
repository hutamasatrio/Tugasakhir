package com.example.tugasakhirnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.example.tugasakhirnew.fragment.VPAdapter
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

//    private lateinit var VPHome : TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentAdapter = VPAdapter(supportFragmentManager)
//        VPHome = findViewById<TabLayout>(R.id.VPHome)
//        VPHome.adapter = fragmentAdapter

    VPHome.
        tabL
    }
}