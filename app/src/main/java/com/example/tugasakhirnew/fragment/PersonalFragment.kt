package com.example.tugasakhirnew.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tugasakhirnew.ProfileActivity
import com.example.tugasakhirnew.R
import com.example.tugasakhirnew.model.UrgentContact
import com.example.tugasakhirnew.model.UserProfile
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.fragment_urgensi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonalFragment : Fragment() {

    private lateinit var ctx: Context
    lateinit var userId: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userId = (ctx as ProfileActivity).getUserId()
        getData()
    }

    private fun getData() {
        RetrofitClient.getApi().getUserbyId(userId)
            .enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile?>, response: Response<UserProfile?>) {
                    val responseBody = response.body()!!
                    val dataEmail = StringBuilder()

                    for (myData in responseBody.data!!) {
                        dataEmail.append(myData?.email)
                        dataEmail.append("/n")
                    }
                    val dataPhone = StringBuilder()
                    for (myData in responseBody.data!!) {
                        dataPhone.append(myData?.phone)
                        dataPhone.append("/n")
                    }

//                txtId3.text = myStringBuilder.toString()
                    tvEmail.text = dataEmail
                    tvPhone.text = dataPhone

                }


                override fun onFailure(call: Call<UserProfile?>, t: Throwable) {
//                Toast.makeText(context@AgensiFragment, "data gagal", Toast.LENGTH_SHORT).show()
//                Log.d("main activity", "failed to load data"+t.message)
                }
            })
    }

}