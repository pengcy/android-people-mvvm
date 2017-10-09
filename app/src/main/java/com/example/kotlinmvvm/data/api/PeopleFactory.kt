package com.example.kotlinmvvm.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object PeopleFactory {

    private val BASE_URL = "http://api.randomuser.me/"
    val RANDOM_USER_URL = "http://api.randomuser.me/?results=10&nat=en"
    val PROJECT_URL = "https://github.com/erikcaffrey/People-MVVM"

    fun create(): PeopleService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
        return retrofit.create(PeopleService::class.java)
    }
}