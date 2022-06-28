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
import com.example.tugasakhirnew.fragment.VPAdapter
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val getID = intent
        val id = getID.getStringExtra("idNFC")

        tvNameID.setText(id)

        getUserList()

        myProfile.setOnClickListener{
            val idNFC = Intent(this@HomeActivity, ProfileActivity::class.java)
            idNFC.putExtra("idNFC2", getID.toString())
            startActivity(idNFC)

            startActivity(Intent(this,ProfileActivity::class.java))


        }

    }





    fun getUserList() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getData()

                if (response.isSuccessful()) {
                  Toast.makeText(this@HomeActivity,"sukses",Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(
                        this@HomeActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }
}