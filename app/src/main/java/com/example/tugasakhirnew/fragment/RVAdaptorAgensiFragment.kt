package com.example.tugasakhirnew.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasakhirnew.R
import com.example.tugasakhirnew.model.DummyItem
import com.example.tugasakhirnew.model.WorkData
import kotlinx.android.synthetic.main.item_agensi_fragment.view.*

class RVAdaptorAgensiFragment (val context: Context,val list: List<WorkData>): RecyclerView.Adapter<RVAdaptorAgensiFragment.ViewHolder>() {
    
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        var job: TextView
        var timeline :TextView
        var workplace: TextView
        var desc :TextView

        init {
            job = itemView.job
            timeline = itemView.timeline
            workplace = itemView.tvWorkPlaceAgensi
            desc = itemView.tvDescriptionWork
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_agensi_fragment,parent,false)
        return ViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.job.text = list[position].posisition
        holder.timeline.text = list[position].timeline
        holder.workplace.text = list[position].workPlace
        holder.desc.text = list[position].description
    }
    override fun getItemCount(): Int {
        return list.size
    }
}