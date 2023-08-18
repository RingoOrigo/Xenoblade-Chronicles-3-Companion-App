package com.example.xenobladechronicles3companion

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//Full content links:
//https://raw.githubusercontent.com/RingoOrigo/RingoOrigo/main/config/XC3C/unique_monsters.json
//https://raw.githubusercontent.com/RingoOrigo/RingoOrigo/main/config/XC3C/side_quests.json
//https://raw.githubusercontent.com/RingoOrigo/RingoOrigo/main/config/XC3C/characters.json
private const val BASE_URL = "https://raw.githubusercontent.com/"
private const val MONSTER_QUERY = "RingoOrigo/RingoOrigo/main/config/XC3C/unique_monsters.json"
private const val SIDE_QUEST_QUERY = "RingoOrigo/RingoOrigo/main/config/XC3C/side_quests.json"
private const val CHARACTER_QUERY = "RingoOrigo/RingoOrigo/main/config/XC3C/characters.json"

//Prep for Future Redeemed (commented out as it is only prep)

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

//Since this file contains a lot of classes that aren't necessarily linked together, classes will start on the same line that related classes are ended. There will be gaps between unrelated classes.
//private const val FR_MONSTER_QUERY = "RingoOrigo/RingoOrigo/main/config/XC3C/fr_unique_monsters.json"
//private const val FR_SIDE_QUEST_QUERY = "RingoOrigo/RingoOrigo/main/config/XC3C/side_quests.json"
//private const val FR_CHARACTER_QUERY = "RingoOrigo/RingoOrigo/main/config/XC3C/characters.json"

interface MonsterAPIService {
    @GET(MONSTER_QUERY)
    fun getMonsters(): Call<MonstersResponse>
} object MonsterAPI {
    val monsterAPI : MonsterAPIService by lazy {
        retrofit.create(MonsterAPIService::class.java)
    }
}

interface SideQuestAPIService {
    @GET(SIDE_QUEST_QUERY)
    fun getSideQuests(): Call<SideQuestResponse>
} object SideQuestAPI {
    val sideQuestAPI : SideQuestAPIService by lazy {
        retrofit.create(SideQuestAPIService::class.java)
    }
}

interface CharacterAPIService {
    @GET(CHARACTER_QUERY)
    fun getCharacters(): Call<CharacterResponse>
} object CharacterAPI {
    val characterAPI : CharacterAPIService by lazy {
        retrofit.create(CharacterAPIService::class.java)
    }
}