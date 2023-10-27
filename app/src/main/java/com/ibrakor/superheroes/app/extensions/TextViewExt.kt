package com.ibrakor.superheroes.app.extensions

import android.graphics.Color
import android.widget.TextView

fun TextView.setAlignmentColor(){
    if (this.text.toString().lowercase() =="bad"){
        this.setTextColor(Color.RED)
    } else {
        this.setTextColor(Color.CYAN)
    }
}