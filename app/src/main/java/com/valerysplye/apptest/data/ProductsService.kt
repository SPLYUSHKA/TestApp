package com.valerysplye.apptest.data

import com.valerysplye.apptest.models.Category
import com.valerysplye.apptest.models.Product
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductsService  {
    @GET("products/categories")
    suspend fun getCategories() : List<Category>

    @GET("products/{id}")
    suspend fun getProducts(@Path("id") id: Int) : List<Product>

}