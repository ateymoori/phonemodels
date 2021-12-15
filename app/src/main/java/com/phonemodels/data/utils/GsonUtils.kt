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

    //convert T to String
    fun Any.toStringByGson(): String = gson.toJson(this)

    //convert gson String to Map<K, V>. Crash when invalid structure found
    inline fun <reified K, reified V> String.toMapByGson(): Map<K, V> = if (isNotEmpty()) {
        Gson().fromJson<HashMap<K, V>>(
            this,
            TypeToken.getParameterized(HashMap::class.java, K::class.java, V::class.java).type
        )
    } else {
        mapOf<K, V>()
    }

    //convert gson String to T. Crash when invalid structure found
    inline fun <reified T> String.toObjectByGson(): T = Gson().fromJson(this, T::class.java)

    inline fun <reified T> Reader.toObjectByGson(): T = Gson().fromJson(this, T::class.java)

    //convert gson String to T. Crash when invalid structure found
    fun <T> String.toObjectByGson(cls: Class<T>): T = gson.fromJson(this, cls)

    fun <T> Reader.toObjectByGson(cls: Class<T>): T = gson.fromJson(this, cls)


    //convert gson String to List<T>. Crash when invalid structure found
    inline fun <reified T> String.toListByGson(): List<T> = if (isNotEmpty()) {
        val fromJson = Gson().fromJson<List<T>>(
            this,
            TypeToken.getParameterized(ArrayList::class.java, T::class.java).type
        )
        fromJson
    } else {
        listOf()
    }

    inline fun <reified T> String.toMutableListByGson(): MutableList<T> = if (isNotEmpty()) {
        Gson().fromJson(
            this,
            TypeToken.getParameterized(ArrayList::class.java, T::class.java).type
        )
    } else {
        mutableListOf()
    }

}