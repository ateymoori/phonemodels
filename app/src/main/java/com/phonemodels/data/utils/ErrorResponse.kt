package com.phonemodels.data.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @Expose
    @SerializedName("type")
    var type: String?,
    @Expose
    @SerializedName("message")
    var message: String?
) {
    fun getText(): String {
        return message ?: type ?: "Error"
    }
}