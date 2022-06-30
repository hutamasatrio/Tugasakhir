package com.example.tugasakhirnew.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasakhirnew.R
import com.example.tugasakhirnew.model.DummyItem
import com.example.tugasakhirnew.model.UrgentContact
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_agensi.*
import kotlinx.android.synthetic.main.fragment_urgensi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UrgensiFragment : Fragment() {

    private val apiInterface = RetrofitClient.getNewtwork().create(ApiInterface::class.java)
    lateinit var rvAdaptorUrgensiFragment: RVAdaptorUrgentFragment
    lateinit var linearlayoutmanager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_urgensi, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData()
        rvAdaptor()
    }

    private fun rvAdaptor() {
        RV_urgent.setHasFixedSize(true)
        linearlayoutmanager = LinearLayoutManager(context)
        RV_urgent.layoutManager = linearlayoutmanager
    }

    private fun getData() {
       /* val user = apiInterface2.getDataContact()
        user.enqueue(object : Callback<UrgentContact?> {
            override fun onResponse( call: Call<UrgentContact?>,response: Response<UrgentContact?>) {
                response.body()?.let {
                    rvAdaptorUrgensiFragment = RVAdaptorUrgentFragment(context!!, it.data)
                    rvAdaptorUrgensiFragment.notifyDataSetChanged()
                    RV_urgent.adapter = rvAdaptorUrgensiFragment
                }
            }

            override fun onFailure(call: Call<UrgentContact?>, t: Throwable) {
//                Toast.makeText(context@AgensiFragment, "data gagal", Toast.LENGTH_SHORT).show()
//                Log.d("main activity", "failed to load data"+t.message)
            }
        })*/
    }
}
