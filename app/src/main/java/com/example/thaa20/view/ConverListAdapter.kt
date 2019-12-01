package com.example.thaa20.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thaa20.R
import com.example.thaa20.model.Conversation
import kotlinx.android.synthetic.main.item_talkingt.view.*

class ConverListAdapter(private val converList: ArrayList<Conversation>) :
    RecyclerView.Adapter<ConverListAdapter.ConverHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_talkingt, parent, false)
        return ConverHolder(view)

    }

    override fun getItemCount() = converList.size


    override fun onBindViewHolder(holder: ConverListAdapter.ConverHolder, position: Int) {
        holder.view.tv_conNum.text = converList[position].numC.toString()
        holder.view.tv_ConvTitle.text = converList[position].title
        holder.view.tv_ConvExplantion.text = converList[position].explanation
    }

    class ConverHolder(val view: View) : RecyclerView.ViewHolder(view)

}