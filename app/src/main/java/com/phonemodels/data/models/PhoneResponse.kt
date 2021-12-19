package com.phonemodels.data.models

import com.google.gson.annotations.SerializedName
import com.phonemodels.domain.entities.PhoneEntity

data class PhonesResponse(val devices: List<PhoneResponse>)

data class PhoneResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("os") val os: String,
    @SerializedName("price") val price: Long,
    @SerializedName("currency") val currency: String,
    @SerializedName("review") val review: Int,
    @SerializedName("status") val status: String,
    @SerializedName("size") val size: String,
    @SerializedName("image") val image: String,
    @SerializedName("isFavorite") val isFavorite: Boolean
)

fun PhonesResponse.mapToPhoneEntities(): List<PhoneEntity> {
    return this.devices.map {
        it.mapToPhoneEntity()
    }
}

fun PhoneResponse.mapToPhoneEntity(): PhoneEntity {
    return PhoneEntity(
        id = id,
        name = name,
        os = os,
        price = price,
        currency = currency,
        review = review,
        status = status,
        size = size,
        image = image,
        isFavorite = isFavorite
    )
}


//for test and compose preview purposes
val SamplePhoneResponse = PhoneResponse(
    id = 123,
    name = "Samsung S21 Ultra",
    os = "Android",
    price = 1200,
    currency = "USD",
    review = 4,
    status = "Available",
    size = "1024 * 768",
    isFavorite = true,
    image = "https://amirteymoori.ir/storage/phones/December2021/jRfEuW5mSkOKzpiytdQN.jpg"
)

