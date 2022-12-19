package com.divvy.service

import com.divvy.domain.Business
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/DivvyPayHQ/BusinessIntelligence/cb0bbbd65cf5804b40018f6dbe013229d9bd1d6c/"
private const val GET_BUSINESSES_PATH = "data.json"

class BusinessRepository {

    interface BusinessService {
        @GET(GET_BUSINESSES_PATH)
        suspend fun getBusinesses(): List<Business>?
    }

    val client by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(BusinessService::class.java)
    }
}