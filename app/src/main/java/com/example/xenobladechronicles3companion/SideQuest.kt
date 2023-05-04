package com.example.xenobladechronicles3companion

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