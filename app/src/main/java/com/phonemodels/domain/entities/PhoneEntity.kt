package com.phonemodels.domain.entities


data class PhoneEntity(
    var id: Int?,
    var name: String?,
    var os: String?,
    var price: Long?,
    var currency: String?,
    var review: Int?,
    var status: String?,
    var size: String?,
    var image: String?
)
