package com.example.tugasakhirnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tugasakhirnew.common.Constant.Companion.NFC_PARAM
import com.example.tugasakhirnew.common.printMessage
import com.example.tugasakhirnew.model.UserProfile
import com.example.tugasakhirnew.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private var NFCId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        intent?.getStringExtra(NFC_PARAM)?.let {
            NFCId = it.trim()
            getData()
        }

        myProfile.setOnClickListener {
            startActivity(
                Intent(this@HomeActivity, ProfileActivity::class.java)
                    .putExtra(NFC_PARAM, NFCId)
            )
        }

        myFriendList.setOnClickListener{
            startActivity(
                Intent(this@HomeActivity, ListPersonActivity::class.java)
                    .putExtra(NFC_PARAM, NFCId)
            )
        }

    }

    private fun getData() {
        RetrofitClient.getApi().getUserbyId(NFCId)
            .enqueue(object : Callback<UserProfile?> {
            override fun onResponse(call: Call<UserProfile?>, response: Response<UserProfile?>) {

                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                Log.i("data_raw", response.toString())
                for (myData in responseBody.data) {
                    myStringBuilder.append(myData.name)
                    myStringBuilder.append("/n")
                }
                tvNameID.text=myStringBuilder
//                Log.i("data_response", myStringBuilder.toString())
//
////                txtId3.text = myStringBuilder.toString()
//                tv.postDelayed({
//                    txtId2.text = myStringBuilder
//                }, 1000)

            }

            override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
                printMessage("data gagal")
                Log.d("main activity", "failed to load data"+t.message)
            }
        })


    }

}