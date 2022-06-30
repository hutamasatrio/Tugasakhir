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
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_agensi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AgensiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgensiFragment : Fragment() {
    private val apiInterface = RetrofitClient.getNewtwork().create(ApiInterface::class.java)
    lateinit var rvAdaptorAgensiFragment : RVAdaptorAgensiFragment
    lateinit var linearlayoutmanager:LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_agensi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        rvAdaptor()
    }

    private fun rvAdaptor() {
        RV_agensi.setHasFixedSize(true)
        linearlayoutmanager = LinearLayoutManager(context)
        RV_agensi.layoutManager = linearlayoutmanager
    }

    private fun getData() {
//        val user = apiInterface2.getDatadummy()
//        user.enqueue(object : Callback<List<DummyItem>?> { override fun onResponse(call: Call<List<DummyItem>?>, response: Response<List<DummyItem>?>) {
//                val responseBody = response.body()!!
//
//                rvAdaptorAgensiFragment = RVAdaptorAgensiFragment(context!!, responseBody)
//                rvAdaptorAgensiFragment.notifyDataSetChanged()
//                RV_agensi.adapter = rvAdaptorAgensiFragment
//
//
//            }
//
//
//            override fun onFailure(call: Call<List<DummyItem>?>, t: Throwable) {
////                Toast.makeText(context@AgensiFragment, "data gagal", Toast.LENGTH_SHORT).show()
////                Log.d("main activity", "failed to load data"+t.message)
//            }
//        })


    }

}