package com.example.tugasakhirnew.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasakhirnew.R
import com.example.tugasakhirnew.model.DummyItem
import kotlinx.android.synthetic.main.item_agensi_fragment.view.*

class RVAdaptorAgensiFragment (val context: Context,val list: List<DummyItem>): RecyclerView.Adapter<RVAdaptorAgensiFragment.ViewHolder>() {
    
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        var userId: TextView
        var title :TextView

        init {
            userId = itemView.job
            title = itemView.timeline
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_agensi_fragment,parent,false)
        return ViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = list[position].id.toString()
        holder.title.text = list[position].title
    }
    override fun getItemCount(): Int {
        return list.size
    }
}