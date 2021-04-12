package com.example.tugasakhirnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugasakhirnew.fragment.VPAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_user_info.TLHome

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val fragmentAdapter = VPAdapter(supportFragmentManager)
        VPProfile.adapter = fragmentAdapter

        TLHome.setupWithViewPager(VPProfile)
    }
}