package com.valerysplye.apptest.di


import android.util.Log
import com.valerysplye.apptest.R
import com.valerysplye.apptest.data.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {

    @Provides
    fun provideRetrofit() : ProductsService {
        return Retrofit.Builder()
            .baseUrl("https://products.310ultraects.keenetic.pro/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsService::class.java)
    }

}