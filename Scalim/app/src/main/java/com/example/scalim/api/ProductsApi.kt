package com.example.scalim.api

import com.example.scalim.Food
import com.example.scalim.FoodWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    // To get all products
    @GET("products.json")
    fun getProductsLists() : Call<List<Food>>

    @GET("{id}/product.json")
    fun getProduct(@Path("id") id: String): Call<FoodWrapper>
}