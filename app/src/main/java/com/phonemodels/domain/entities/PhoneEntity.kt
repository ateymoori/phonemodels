package com.phonemodels.domain.entities

data class PhoneEntity(
    var id: Int? = null,
    var name: String? = null,
    var os: String? = null,
    var price: Long? = null,
    var currency: String? = null,
    var review: Int? = null,
    var status: String? = null,
    var size: String? = null,
    var image: String? = null
)
