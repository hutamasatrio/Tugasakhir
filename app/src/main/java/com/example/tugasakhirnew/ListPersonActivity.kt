package com.example.tugasakhirnew

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasakhirnew.common.Constant
import com.example.tugasakhirnew.common.Constant.Companion.NFC_PARAM
import com.example.tugasakhirnew.common.ExecuteAsync
import com.example.tugasakhirnew.common.printMessage
import com.example.tugasakhirnew.model.DataProfile
import com.example.tugasakhirnew.model.FriendList
import com.example.tugasakhirnew.model.FriendListData
import com.example.tugasakhirnew.model.UserProfile
import com.example.tugasakhirnew.network.ApiInterface
import com.example.tugasakhirnew.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_list_person.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPersonActivity : AppCompatActivity() {
    lateinit var rvAdaptorFriendList : RVAdaptorListFriend
    lateinit var linearlayoutmanager: LinearLayoutManager


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

        getData()
    }

    private fun getData() {
        val id = intent.getStringExtra(Constant.NFC_PARAM)

        RetrofitClient.getApi().getFriendListbyId(id!!)
            .enqueue(object : Callback<FriendList?> {
                override fun onResponse(call: Call<FriendList?>, response: Response<FriendList?>) {
                    response.body()?.let {
                        callUserProfile(it.data)
                    }
                }

                override fun onFailure(call: Call<FriendList?>, t: Throwable) {
                    printMessage("data gagal")
                    Log.d("main activity", "failed to load data"+t.message)
                }
            })
    }
    private val listUsers = ArrayList<DataProfile>()

    private fun callUserProfile(list: List<FriendListData>) {
        val api = RetrofitClient.getApi()

        // run in Background Thread
        ExecuteAsync.runInBackground(object : ExecuteAsync.Actions{
            override fun doInBacground() {
                for (i in list.indices) {
                    toServer(i == list.size - 1, api, list[i].friendId)
                }
            }
        })
    }

    // run in Background Thread
    private fun toServer(isDone: Boolean, api: ApiInterface, userId: String)  {
        api.getUserbyId(userId).enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
              response.body()?.let {
                  listUsers.add(it.data[it.data.size - 1])
                  if (isDone) runOnUi()
              }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                t.message?.let { Log.i("ff_background", it) }
            }

        })
    }

    private fun runOnUi() {
        // run in UI Thread
        ExecuteAsync.runInUiThread(listUsers,
            object : ExecuteAsync.OnSuccess<ArrayList<DataProfile>> {
                override fun onSuccess(response: ArrayList<DataProfile>) {
                    setToRv(response)
                }
            }
        )
    }

    private fun setToRv(profile: List<DataProfile>) {
        rvAdaptorFriendList = RVAdaptorListFriend(this, profile)
        rvAdaptorFriendList.notifyDataSetChanged()
        RV_ListFriend.adapter = rvAdaptorFriendList
    }
}