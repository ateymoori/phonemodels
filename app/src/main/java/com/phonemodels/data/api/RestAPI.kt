package com.phonemodels.data.api

import com.phonemodels.data.models.PhoneResponse
import com.phonemodels.data.models.PhonesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAPI {

    companion object {
        const val BASE_API_URL = "https://amirteymoori.ir/api/"
    }

    @GET("v1/phones")
    suspend fun getPhones(): Response<PhonesResponse>

    @GET("v1/phones/find")
    suspend fun findPhonesByNameOrOS(@Query("word") word: String?): Response<PhonesResponse>

    @GET("v1/phones/{id}")
    suspend fun getPhoneDetails(@Path("id") id: Int?): Response<PhoneResponse>

    //switch the isFavorite by each API call
    @POST("v1/phones/{id}/switch_favorite")
    suspend fun switchPhoneFavorites(@Path("id") id: Int?): Response<PhoneResponse>

}


