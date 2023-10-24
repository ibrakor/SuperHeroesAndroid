package com.ibrakor.superheroes.data.local

import android.content.Context
import com.ibrakor.avilaentapaspractica.app.serialization.JsonSerialization

class BiographyLocalSource (private val context: Context, private val jsonSerialization: JsonSerialization){

    val sharedPreferences = context.getSharedPreferences("biographies",Context.MODE_PRIVATE)


}