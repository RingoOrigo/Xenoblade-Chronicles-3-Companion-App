package com.example.xenobladechronicles3companion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class Monster (
    @Json(name = "name") val name : String,
    @Json(name = "level") val level : Long,
    @Json(name = "location") val location : String,
    @Json(name = "region") val region : String,
    @Json(name = "superboss") val superboss : Boolean,
    @Json(name = "articleurl") val articleURL : String,
    @Json(name = "imageurl") val imageURL : String,
    var defeated : Boolean = false){}

data class SideQuest (
    val questName : String,
    val recLevel : Long,
    val reqCharacter : String?,
    val region : String,
    val location : String,
    val DLC : Boolean?,
    val heroQuest : Boolean?,
    val imageURL : String,
    val articleURL : String,
    var completed : Boolean
){
}
