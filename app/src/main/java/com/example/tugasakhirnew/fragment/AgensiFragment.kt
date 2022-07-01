package com.example.tugasakhirnew.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasakhirnew.ProfileActivity
import com.example.tugasakhirnew.R
import com.example.tugasakhirnew.model.DummyItem
import com.example.tugasakhirnew.model.UrgentContact
import com.example.tugasakhirnew.model.Work
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_agensi.*
import kotlinx.android.synthetic.main.fragment_urgensi.*
import org.jetbrains.anko.support.v4.ctx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AgensiFragment : Fragment() {

    lateinit var rvAdaptorAgensiFragment : RVAdaptorAgensiFragment
    lateinit var linearlayoutmanager:LinearLayoutManager

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

        return inflater.inflate(R.layout.fragment_agensi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = (ctx as ProfileActivity).getUserId()
        getData()
        rvAdaptor()
    }


    private fun rvAdaptor() {
        RV_agensi.setHasFixedSize(true)
        linearlayoutmanager = LinearLayoutManager(context)
        RV_agensi.layoutManager = linearlayoutmanager
    }

    private fun getData() {
        RetrofitClient.getApi().getWorkbyId(userId)
            .enqueue(object : Callback<Work?> {
                override fun onResponse(call: Call<Work?>, response: Response<Work?>) {

                    response.body()?.let {
                        rvAdaptorAgensiFragment = RVAdaptorAgensiFragment(context!!, it.data)
                        rvAdaptorAgensiFragment.notifyDataSetChanged()
                        RV_agensi.adapter = rvAdaptorAgensiFragment

                    }
                }

                override fun onFailure(call: Call<Work?>, t: Throwable) {
                    print("data gagal")
                    Log.d("main activity", "failed to load data"+t.message)
                }
            })
    }



}