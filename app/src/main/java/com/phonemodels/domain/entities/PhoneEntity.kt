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
    var image: String? = null,
    var isFavorite: Boolean? = null
)

//for test and compose preview purposes
val SamplePhoneEntity = PhoneEntity(
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