package com.example.xenobladechronicles3companion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Monster (
    val name : String,
    val level : Int,
    val location : String,
    val region : String,
    val superboss : Boolean?,
    val articleURL : String,
    val imageURL : String,
    var defeated : Boolean){}

data class MonsterJson (
    val name : String,
    val level : Int,
    val location : String,
    val region : String,
    val superboss : Boolean?,
    val articleURL: String,
    val imageURL: String
        )

