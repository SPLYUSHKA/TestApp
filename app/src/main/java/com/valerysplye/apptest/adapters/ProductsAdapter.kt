package com.valerysplye.apptest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.valerysplye.apptest.R
import com.valerysplye.apptest.models.Product

class ProductsAdapter(private var data : List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val priceButton : AppCompatButton = itemView.findViewById(R.id.product_price)
        val nameText : TextView = itemView.findViewById(R.id.product_name)
        val descriptionText : TextView = itemView.findViewById(R.id.product_description)
        val productPhoto : ImageView = itemView.findViewById(R.id.product_photo)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun loadProducts(newData : List<Product>)
    {
      data = newData
      notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = data[position];
        holder.nameText.text = product.name
        holder.priceButton.text = "от ${product.price} р"
        holder.descriptionText.text = product.description
        Picasso.get()
            .load("https://products.310ultraects.keenetic.pro/${product.image}")
            .into(holder.productPhoto)
    }

    override fun getItemCount(): Int {
        return data.size;
    }
}