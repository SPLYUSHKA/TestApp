package com.valerysplye.apptest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.valerysplye.apptest.R

class BannersAdapter(private val banners : List<Int>) : RecyclerView.Adapter<BannersAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var imageBanner : ImageView = itemView.findViewById(R.id.banner_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageBanner.setImageResource(banners[position]);
    }

    override fun getItemCount(): Int {
       return banners.size
    }
}