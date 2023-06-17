package com.valerysplye.apptest.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.valerysplye.apptest.R
import com.valerysplye.apptest.models.Category
import com.valerysplye.apptest.models.Product
import com.valerysplye.apptest.ui.menu.MenuViewModel

class CategoriesAdapter(private var data: List<Category>,
                        private val menuViewModel: MenuViewModel
    ) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val chips : MutableList<Chip> = mutableListOf()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryChip : Chip = itemView.findViewById(R.id.category_chip)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun loadCategories(newData : List<Category>)
    {
        data = newData
        notifyDataSetChanged()

    }
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryChip.apply {
            text =data[position].name
            isCheckable = true
            isCheckedIconVisible = false


            setOnClickListener {
                menuViewModel.loadProducts(data[position].categoryId)
                chips.forEach { it.isChecked = false }
                holder.categoryChip.isChecked = true
            }

            setOnCheckedChangeListener { _, cheaked ->
                if(cheaked)
                {
                    setTextColor(resources.getColor(R.color.darkpink))

                    setChipBackgroundColorResource(R.color.lightpink)
                }
                else
                {
                    setTextColor(resources.getColor(R.color.gray))
                    setChipBackgroundColorResource(R.color.white)
                }
            }
        }

        chips.add(holder.categoryChip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}