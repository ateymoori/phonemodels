package com.cocktails.data.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @Expose
    @SerializedName("error")
    var error: String?,
    @Expose
    @SerializedName("error_description")
    var desc: String?,
    @Expose
    @SerializedName("message")
    var message: String?,
    @Expose
    @SerializedName("detail")
    var detail: String?
) {
    fun getText(): String {
        return if (error.isNullOrEmpty())
            if (detail.isNullOrEmpty())
                if (message.isNullOrEmpty())
                    ""
                else message.toString()
            else detail.toString()
        else error.toString()
    }
}