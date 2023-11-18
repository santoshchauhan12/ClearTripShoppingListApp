package com.android.shoppingcart.repository

import android.util.Log
import com.android.shoppinglist.model.bitcoin.CryptoData
import com.android.shoppinglist.model.bitcoin.logo.MetaDataResponse
import com.android.shoppinglist.network.ApiInterface
import com.android.shoppinglist.util.AppResult
import com.android.shoppinglist.util.Utils.handleApiError
import com.android.shoppinglist.util.Utils.handleSuccess
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ExchangeRepository {



    var interceptor =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(HeaderInterceptor())
        .build()
    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header("Content-Type", "application/json")  // Example header
                .header("X-CMC_PRO_API_KEY", "3a54e6b5-676c-4575-8f81-9527e73de6af")  // Example authorization header
                .build()

            return chain.proceed(modifiedRequest)
        }
    }

    var retrofit = Retrofit.Builder()
        .baseUrl("https://pro-api.coinmarketcap.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()

    var service: ApiInterface = retrofit.create(ApiInterface::class.java)
//    fun getShoppingList(): MutableList<ShoppingItem> {
//
//        var serviceRequestCall =  service.getShoppingList()
//        serviceRequestCall.enqueue(object : Callback<MutableList<ShoppingItem>> {
//
//            override fun onResponse(
//                call: Call<MutableList<ShoppingItem>>,
//                response: Response<MutableList<ShoppingItem>>
//            ) {
//                if(response.isSuccessful) {
//                    return response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<MutableList<ShoppingItem>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//
//        return null
//    }

//    suspend fun getBitCoinList(): AppResult<CryptoData> {
//        Log.e("cryptocurr", " inside repository ")
//
//        var resultResponse : AppResult<CryptoData>?= null
////        if (isOnline()) {
//            return try {
//                val response = service.getBitCoinList()
//
//                response.enqueue(object : Callback<CryptoData?> {
//                    override fun onResponse(call: Call<CryptoData?>?, response: Response<CryptoData?>?) {
//                        // Handle success
//                      resultResponse =   handleSuccess(response)
//                    }
//
//                    override fun onFailure(call: Call<CryptoData?>?, t: Throwable?) {
//                        // Handle failure
//                        return  handleSuccess(response)
//                    }
//                })
//                return  handleSuccess(response)
////                if (response.isSuccessful) {
////                    //save the data
//////                    response.body()?.let {
//////                        withContext(Dispatchers.IO) { dao.add(it) }
//////                    }
////                    Log.e("cryptocurr", " inside success ")
////
////                    handleSuccess(response)
////                } else {
////                    Log.e("cryptocurr", " inside error ")
////
////                    handleApiError(response)
////                }
//            } catch (e: Exception) {
//                Log.e("cryptocurr", " inside exception "+ e.printStackTrace())
//
//                AppResult.Error(e)
//            }
////        } else {
////
////            return noNetworkConnectivityError()
////            //check in db if the data exists
//////            val data = getCountriesDataFromCache()
//////            return if (data.isNotEmpty()) {
//////                Log.d(TAG, "from db")
//////                AppResult.Success(data)
//////            } else
//////            //no network
//////                context.noNetworkConnectivityError()
////        }
//    }

     suspend fun getCryptoList(): AppResult<CryptoData> {

        var response = service.getBitCoinList()
        if (service.getBitCoinList().isSuccessful) {

            Log.e("cryptocurr", " inside success ")

            return handleSuccess(response)
        } else {
            Log.e("cryptocurr", " inside error ")

            return handleApiError(response)
        }
    }

    suspend fun getLogoUrlList(ids: String): AppResult<MetaDataResponse> {

        var response = service.getMetaDataList(ids)
        if (response.isSuccessful) {

            Log.e("cryptocurr", " inside success ")

            return handleSuccess(response)
        } else {
            Log.e("cryptocurr", " inside error ")

            return handleApiError(response)
        }
    }
}