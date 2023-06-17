package com.valerysplye.apptest.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.valerysplye.apptest.R
import com.valerysplye.apptest.adapters.BannersAdapter
import com.valerysplye.apptest.adapters.CategoriesAdapter
import com.valerysplye.apptest.adapters.ProductsAdapter
import com.valerysplye.apptest.databinding.FragmentMenuBinding
import com.valerysplye.apptest.models.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val menuViewModel =
            ViewModelProvider(this).get(MenuViewModel::class.java)

        binding = FragmentMenuBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val bannersConteiner = binding.bannerList
        val bannersAdapter = BannersAdapter(listOf(R.drawable.banner,R.drawable.banner))
        bannersConteiner.adapter = bannersAdapter
        bannersConteiner.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        val categoriesContainer = binding.categorySelector;
        val categoriesAdapter = CategoriesAdapter(listOf(Category(0,"...")),menuViewModel)
        categoriesContainer.adapter = categoriesAdapter
        categoriesContainer.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        val productsContainer = binding.productsList;
        val productsAdapted = ProductsAdapter(emptyList())
        productsContainer.adapter = productsAdapted
        productsContainer.layoutManager = LinearLayoutManager(this.context)


        menuViewModel.categories.observe(viewLifecycleOwner) {
            categoriesAdapter.loadCategories(it)
            menuViewModel.loadProducts(it.first().categoryId)

        }

        menuViewModel.products.observe(viewLifecycleOwner) {
           productsAdapted.loadProducts(it)

        }
        menuViewModel.loadCategories()
        return root
    }


}