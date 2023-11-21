package com.ibrakor.avilaentapaspractica.app.serialization

import com.google.gson.Gson
import javax.inject.Inject

class GsonSerialization @Inject constructor(private val gson: Gson) : JsonSerialization {

    override fun <T> toJson(obj: T, typeClass: Class<T>): String {
        return gson.toJson(obj, typeClass)
    }

    override fun <T> fromJson(json: String, typeClass: Class<T>): T {
        return gson.fromJson(json, typeClass)
    }
}