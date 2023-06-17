package com.valerysplye.apptest.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valerysplye.apptest.data.ProductsService
import com.valerysplye.apptest.models.Category
import com.valerysplye.apptest.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val productsService: ProductsService) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories : LiveData<List<Category>>
        get() = _categories

    private val _selectedCategory = MutableLiveData<Category>()
    val selectedCategory : LiveData<Category>
        get() = _selectedCategory

    private val _products = MutableLiveData<List<Product>>()
    val products : LiveData<List<Product>>
        get() = _products


    fun loadProducts(id : Int) {
        _products.value = emptyList()
        viewModelScope.launch {
            _products.value = productsService.getProducts(id)
        }
    }

    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = productsService.getCategories()
        }
    }

}