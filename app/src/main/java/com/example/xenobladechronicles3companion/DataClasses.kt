package com.example.xenobladechronicles3companion

import com.squareup.moshi.Json

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
    @Json(name = "quest_name") val questName : String,
    @Json(name = "recommended_level") val recLevel : Long,
    @Json(name = "required_character") val reqCharacter : String?,
    @Json(name = "region") val region : String,
    @Json(name = "location") val location : String,
    @Json(name = "dlc") val DLC : Boolean?,
    @Json(name = "hero_quest") val heroQuest : Boolean?,
    @Json(name = "imageurl") val imageURL : String,
    @Json(name = "articleurl") val articleURL : String,
    var completed : Boolean){}

data class Character (
    val name : String,
    val kevesi : Boolean?,
    val agnian : Boolean?,
    val moebius : Boolean?,
    val ouroboros : Boolean?,
    val startingClass : String?,
    val hero : Boolean?,
    val playable : Boolean?,
    val DLC : Boolean?,
    val articleURL : String,
    val imageURL : String
        ){}
