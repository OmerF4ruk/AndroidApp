package com.example.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app.HerbRecyclerAdapter.*

class HerbRecyclerAdapter:RecyclerView.Adapter<HerbHolder>() {
    class HerbHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil=object :DiffUtil.ItemCallback<Herb>(){

        override fun areItemsTheSame(oldItem: Herb, newItem: Herb): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Herb, newItem: Herb): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var herbs: List<Herb>
        get() = recyclerListDiffer.currentList
        set(value)= recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerbHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return HerbHolder(view)
    }

    override fun onBindViewHolder(holder: HerbHolder, position: Int) {
        val herbName=holder.itemView.findViewById<TextView>(R.id.nameText)
        val herbDate=holder.itemView.findViewById<TextView>(R.id.dateText)
        herbName.text="${herbs.get(position).name}"
        herbDate.text="${herbs.get(position).date}"

    }

    override fun getItemCount(): Int {
        return herbs.size
    }

}