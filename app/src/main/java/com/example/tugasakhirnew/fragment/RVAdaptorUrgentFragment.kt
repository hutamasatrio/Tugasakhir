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
        var name: TextView
        var address :TextView
        var phone :TextView

        init {
            name = itemView.tvNameContact
            address = itemView.tvAddressContact
            phone = itemView.tvPhoneContact
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_urgent_contact,parent,false)
        return ViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position]?.let {
            holder.name.text = it.urgentName
            holder.address.text = it.urgentAddress
            holder.phone.text = it.noTelpon
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
}