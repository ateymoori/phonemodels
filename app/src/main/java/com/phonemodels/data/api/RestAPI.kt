package com.phonemodels.data.api

import com.phonemodels.data.models.PhoneResponse
import com.phonemodels.data.models.PhonesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAPI {

    @GET("phones")
    suspend fun getPhones(): Response<PhonesResponse>

    @GET("phones/find")
    suspend fun findPhonesByNameOrOS(@Query("word") word: String?): Response<PhonesResponse>

    @GET("phones/{id}")
    suspend fun getPhoneDetails(@Path("id") id: Int?): Response<PhoneResponse>

}


