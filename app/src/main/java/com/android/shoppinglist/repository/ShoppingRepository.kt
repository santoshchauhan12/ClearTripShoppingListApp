package com.android.shoppingcart.repository

import com.android.shoppingcart.model.ShoppingItem
import com.android.shoppingcart.network.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ShoppingRepository {


    var interceptor =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    var retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    var service: ApiInterface = retrofit.create(ApiInterface::class.java)
    fun getShoppingList(): MutableList<ShoppingItem> {

        var serviceRequestCall =  service.getShoppingList()
        serviceRequestCall.enqueue(object : Callback<MutableList<ShoppingItem>> {

            override fun onResponse(
                call: Call<MutableList<ShoppingItem>>,
                response: Response<MutableList<ShoppingItem>>
            ) {
                if(response.isSuccessful) {
                    return response.body()
                }
            }

            override fun onFailure(call: Call<MutableList<ShoppingItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return
    }
}