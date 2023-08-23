package com.android.shoppingcart.network

import com.android.shoppingcart.model.ShoppingItem
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {

    @GET("/products")
    fun getShoppingList(): Call<MutableList<ShoppingItem>>

}