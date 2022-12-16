package com.divvy.service

import com.divvy.domain.Business
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class BusinessRepository {

    interface BusinessService {
        @GET("data.json")
        suspend fun getBusinesses(): List<Business>?
    }

    val client by lazy {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/DivvyPayHQ/BusinessIntelligence/cb0bbbd65cf5804b40018f6dbe013229d9bd1d6c/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(BusinessService::class.java)
    }
}