package com.example.xenobladechronicles3companion

import com.squareup.moshi.Json

class MonstersResponse {
    @Json(name="monsters")
    lateinit var monsterList : List<Monster>
    //So fun fact, if nothing is nullable in your response, you can just straight up annotate the response into the class itself... No properties class necessary.
}

class SideQuestResponse {
    @Json (name = "quests")
    lateinit var sideQuestList : List<SideQuestProperties>
}

class SideQuestProperties {
    @Json (name = "quest_name")
    var questName : String = ""

    @Json (name = "location")
    var location : String = ""

    @Json (name = "region")
    var region : String = ""

    @Json (name = "recommended_level")
    var recLevel : Long = 0

    @Json (name = "required_character")
    var reqCharacter : String? = ""

    @Json (name = "dlc")
    var DLC : Boolean? = false

    @Json (name = "hero_quest")
    var heroQuest : Boolean? = false

    @Json (name = "imageurl")
    var imageURL : String = ""

    @Json (name = "articleurl")
    var articleURL : String = ""
}
