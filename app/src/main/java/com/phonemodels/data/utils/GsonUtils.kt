package com.phonemodels.data.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.Reader

object GsonUtils {

    val gson: Gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .setLenient()
        .serializeNulls()
        .create()


    inline fun <reified T> Reader.toObjectByGson(): T = Gson().fromJson(this, T::class.java)

    fun <T> Reader.toObjectByGson(cls: Class<T>): T = gson.fromJson(this, cls)

}