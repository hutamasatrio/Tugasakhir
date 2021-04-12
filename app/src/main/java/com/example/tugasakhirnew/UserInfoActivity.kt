package com.example.tugasakhirnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugasakhirnew.fragment.VPAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

//        val fragmentAdapter = VPAdapter(supportFragmentManager)
//        VPProfile.adapter = fragmentAdapter
//
//        TLHome.setupWithViewPager(VPProfile)
    }
}