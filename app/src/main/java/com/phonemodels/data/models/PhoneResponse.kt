package com.phonemodels.data.models

import com.google.gson.annotations.SerializedName


data class PhonesResponse(val devices: List<PhoneResponse> )

data class PhoneResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("os") val os: String,
    @SerializedName("price") val price: Long,
    @SerializedName("currency") val currency: String,
    @SerializedName("review") val review: Int,
    @SerializedName("status") val status: String,
    @SerializedName("size") val size: String,
    @SerializedName("image") val image: String
)

