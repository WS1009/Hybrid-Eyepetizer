package com.ws.eyepetizer.provider.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetConfig {

    fun Retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://baobab.kaiyanapp.com/api/")
//            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create()) //gson转换器
            .build()
    }
}