package com.divvy.service

import com.divvy.domain.Business
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class BusinessClient {

    interface BusinessService {
        @GET("data.json")
        suspend fun getBusinesses(): List<Business>?
    }

    val service by lazy {
        Retrofit.Builder()
            .baseUrl("https://github.com/DivvyPayHQ/BusinessIntelligence/blob/cb0bbbd65cf5804b40018f6dbe013229d9bd1d6c/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}