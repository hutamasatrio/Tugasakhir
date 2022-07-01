package com.example.tugasakhirnew

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasakhirnew.common.Constant
import com.example.tugasakhirnew.model.DataProfile
import com.example.tugasakhirnew.model.FriendListData
import kotlinx.android.synthetic.main.item_agensi_fragment.view.*
import kotlinx.android.synthetic.main.item_list_friend.view.*

class RVAdaptorListFriend (val context: Context, val list: List<DataProfile>): RecyclerView.Adapter<RVAdaptorListFriend.ViewHolder>() {

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        var userId: TextView
        var title :TextView
        var alamat :TextView


        init {
            userId = itemView.tvItemListFriendName
            title = itemView.tvItemListFrienduserid
            alamat =itemView.tvItemListAlamat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_list_friend,parent,false)

        return ViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = list[position].userId
        holder.userId.text = list[position].name
        holder.title.text = list[position].phone
        holder.alamat.text = list[position].address as CharSequence?
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("nfc_id", id)
            context.startActivity(intent)
//            startActivity(contexintentt)
        })
    }
    override fun getItemCount(): Int {
        return list.size
    }
}