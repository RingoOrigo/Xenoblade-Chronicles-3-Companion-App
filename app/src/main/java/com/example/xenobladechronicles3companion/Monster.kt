package com.example.xenobladechronicles3companion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class Monster (
    @Json(name = "name") val name : String,
    @Json(name = "level") val level : Long,
    @Json(name = "location") val location : String,
    @Json(name = "region") val region : String,
    @Json(name = "superboss") val superboss : Boolean,
    @Json(name = "articleurl") val articleurl : String,
    @Json(name = "imageurl") val imageurl : String,
    var defeated : Boolean = false){}
