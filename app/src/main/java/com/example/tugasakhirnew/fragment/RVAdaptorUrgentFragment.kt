package com.example.tugasakhirnew.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasakhirnew.R
import com.example.tugasakhirnew.model.DataUrgentContact
import com.example.tugasakhirnew.model.DummyItem
import com.example.tugasakhirnew.model.UrgentContact
import kotlinx.android.synthetic.main.item_agensi_fragment.view.*
import kotlinx.android.synthetic.main.item_urgent_contact.view.*

class RVAdaptorUrgentFragment(val context: Context, val list: List<DataUrgentContact?>): RecyclerView.Adapter<RVAdaptorUrgentFragment.ViewHolder>() {
    
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        var userId: TextView
        var title :TextView

        init {
            userId = itemView.tvRelationID
            title = itemView.tvEmailId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_urgent_contact,parent,false)
        return ViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position]?.let {
            holder.userId.text = it.urgentName
            holder.title.text = it.urgentAddress
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
}