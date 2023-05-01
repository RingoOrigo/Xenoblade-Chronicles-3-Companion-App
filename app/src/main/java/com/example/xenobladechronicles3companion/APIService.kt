package com.example.xenobladechronicles3companion

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

//https://api.github.com/repos/RingoOrigo/Xenoblade-Chronicles-3-Companion-App/contents/blob/master/app/src/main/res/raw/unique_monsters.json
//https://raw.githubusercontent.com/RingoOrigo/Xenoblade-Chronicles-3-Companion-App/master/app/src/main/res/raw/unique_monsters.json
private const val BASE_URL = "https://raw.githubusercontent.com/"
private const val MONSTER_QUERY = "RingoOrigo/Xenoblade-Chronicles-3-Companion-App/master/app/src/main/res/raw/unique_monsters.json"
private const val SIDE_QUEST_QUERY = "RingoOrigo/Xenoblade-Chronicles-3-Companion-App/master/app/src/main/res/raw/side_quests.json"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface MonsterAPIService {
    @GET(MONSTER_QUERY)
    fun getMonsters(): Call<MonstersResponse>
}

object MonsterAPI {
    val monsterAPI : MonsterAPIService by lazy {
        retrofit.create(MonsterAPIService::class.java)
    }
}


interface SideQuestAPIService {
    @GET(SIDE_QUEST_QUERY)
    fun getSideQuests(): Call<SideQuestResponse>
}

object SideQuestAPI {
    val sideQuestAPI : SideQuestAPIService by lazy {
        retrofit.create(SideQuestAPIService::class.java)
    }
}