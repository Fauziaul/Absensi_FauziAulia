package org.d3if0097.fingger.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.118/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getInstace(): ApiClient{
        return getRetrofitClient().create(ApiClient::class.java)
    }
}