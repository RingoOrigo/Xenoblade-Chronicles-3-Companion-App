package com.example.xenobladechronicles3companion

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//https://api.github.com/repos/RingoOrigo/Xenoblade-Chronicles-3-Companion-App/contents/blob/master/app/src/main/res/raw/unique_monsters.json
//https://raw.githubusercontent.com/RingoOrigo/Xenoblade-Chronicles-3-Companion-App/master/app/src/main/res/raw/unique_monsters.json
private const val BASE_URL = "https://raw.githubusercontent.com/"
private const val QUERY_STRING = "RingoOrigo/Xenoblade-Chronicles-3-Companion-App/master/app/src/main/res/raw/unique_monsters.json"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface MonsterAPIService {
    @GET(QUERY_STRING)
    fun getMonsters(): Call<MonstersResponse>
}

object MonsterAPI {
    val monsterAPI : MonsterAPIService by lazy {
        retrofit.create(MonsterAPIService::class.java)
    }
}