package com.example.xenobladechronicles3companion

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Monster (
    val name : String,
    val level : Int,
    val location : String,
    val region : String,
    var superboss : Boolean,
    val defeated : Boolean){}
