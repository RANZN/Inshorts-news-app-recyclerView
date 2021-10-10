package com.ranzan.inshortsnewsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class RecyclerViewAdapter(private val list: List<DataItem>) :
    RecyclerView.Adapter<RecyclerViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return RecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int = list.size

}

class RecyclerViewViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(dataItem: DataItem) {
        view.run {
            Glide.with(imgView).load(dataItem.imageUrl).placeholder(R.drawable.ic_image).into(imgView)
            tvTitle.text = dataItem.title
            tvContent.text = dataItem.content
            date.text = dataItem.date + " " + dataItem.time

        }
    }
}