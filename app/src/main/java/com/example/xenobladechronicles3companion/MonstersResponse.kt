package com.example.xenobladechronicles3companion

import com.squareup.moshi.Json

class MonstersResponse {
    @Json(name="monsters")
    lateinit var monsterList : List<Monster>
}

class MonsterProperties {
    @Json (name = "name")
    var name : String = ""

    @Json (name = "location")
    var location : String = ""

    @Json (name = "region")
    var region : String = ""

    @Json (name = "level")
    var level : Long = 0

    @Json (name = "superboss")
    var superboss : Boolean? = false

    @Json (name = "articleurl")
    var articleurl : String = ""

    @Json (name = "imageurl")
    var imageurl : String = ""
}