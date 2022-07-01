package com.example.tugasakhirnew

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tugasakhirnew.common.Constant.Companion.NFC_PARAM
import com.example.tugasakhirnew.fragment.VPAdapter
import com.example.tugasakhirnew.model.UserProfile
import com.example.tugasakhirnew.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_user_info.TLHome
import kotlinx.android.synthetic.main.activity_user_info.VPProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        userId = intent.getStringExtra(NFC_PARAM).toString()

        val fragmentAdapter = VPAdapter(supportFragmentManager)
        VPProfile.adapter = fragmentAdapter
        getData()
        TLHome.setupWithViewPager(VPProfile)
    }

    fun getUserId() = userId


    private fun getData() {
        RetrofitClient.getApi().getUserbyId(userId)
            .enqueue(object : Callback<UserProfile> {
                override fun onResponse(
                    call: Call<UserProfile?>,
                    response: Response<UserProfile?>
                ) {
                    val responseBody = response.body()!!

//
//                    responseBody.let {
//                        it.data.
//                    }
//

                    val dataProfileNama = StringBuilder()
                    for (myData in responseBody.data) {
                        dataProfileNama.append(myData.name)
                    }


                    val dataProfileWorkLabel = StringBuilder()
                    for (myData in responseBody.data) {
                        dataProfileWorkLabel.append(myData.workLabel)
                    }

                    val dataProfileAge = StringBuilder()
                    for (myData in responseBody.data) {
                        dataProfileAge.append(myData.email)
                    }

                    val dataProfileDomicily = StringBuilder()
                    for (myData in responseBody.data) {
                        dataProfileDomicily.append(myData.email)
                    }


//
                    tvNamaActivityProfile.text = dataProfileNama
                    tvDomicilyActivityProfile.text = dataProfileDomicily
                    tvAgeActivityProfile.text = dataProfileAge
                    tvWorkLabelActivityProfile.text = dataProfileWorkLabel
                }


                override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
//                Toast.makeText(context@AgensiFragment, "data gagal", Toast.LENGTH_SHORT).show()
//                Log.d("main activity", "failed to load data"+t.message)
                }
            })
    }


}