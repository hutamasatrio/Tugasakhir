package com.example.tugasakhirnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tugasakhirnew.common.Constant
import com.example.tugasakhirnew.common.Constant.Companion.NFC_PARAM
import kotlinx.android.synthetic.main.activity_list_person.*

class ListPersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_person)
        val id = intent.getStringExtra(Constant.NFC_PARAM)

        addFrriend.setOnClickListener{
            Log.i("video", "ListPersonActivity : " + id)
            startActivity(
                Intent(this,AddFriendActivity::class.java)
                    .putExtra(NFC_PARAM, id)
            )
        }
    }
}