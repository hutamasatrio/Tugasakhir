package com.example.tugasakhirnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.tugasakhirnew.common.Constant.Companion.NFC_PARAM
import com.example.tugasakhirnew.fragment.VPAdapter
import com.example.tugasakhirnew.model.UserProfile
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*
import org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class HomeActivity : AppCompatActivity() {

    private val list = ArrayList<UserProfile>()
    private val retrofitClient = RetrofitClient.getNewtwork().create(ApiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getSocketFactory()
        getData()

        val getID = intent
        val id = getID.getStringExtra(NFC_PARAM)?.trim()
//        tvNameID.setText(id)


//
//        var retrofit = RetrofitClient.getInstance()
//        var apiInterface = retrofit.create(ApiInterface::class.java)
//        apiInterface.getData()






        myProfile.setOnClickListener {
            val idNFC = Intent(this@HomeActivity, ProfileActivity::class.java)
            idNFC.putExtra(NFC_PARAM, id)
            startActivity(idNFC)
        }

        myFriendList.setOnClickListener{
            val idNFC = Intent(this@HomeActivity, ListPersonActivity::class.java)
            idNFC.putExtra(NFC_PARAM, id)
            startActivity(idNFC)
        }

    }





    private fun getData() {

        val profileUser = retrofitClient.getData()
        profileUser.enqueue(object : Callback<UserProfile?> {
            override fun onResponse(call: Call<UserProfile?>, response: Response<UserProfile?>) {

                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                Log.i("data_raw", response.toString())
                for (myData in responseBody.data) {
                    myStringBuilder.append(myData.name)
                    myStringBuilder.append("/n")
                }
//                Log.i("data_response", myStringBuilder.toString())
//
////                txtId3.text = myStringBuilder.toString()
//                tv.postDelayed({
//                    txtId2.text = myStringBuilder
//                }, 1000)

            }

            override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "data gagal", Toast.LENGTH_SHORT).show()
                Log.d("main activity", "failed to load data"+t.message)
            }
        })


    }

}