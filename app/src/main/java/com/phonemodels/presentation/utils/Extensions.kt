package com.phonemodels.presentation.utils

import android.util.Log

fun String?.log(tag: String? = "app_debug"): String? {
    Log.d(tag, this ?: "")
    return this
}