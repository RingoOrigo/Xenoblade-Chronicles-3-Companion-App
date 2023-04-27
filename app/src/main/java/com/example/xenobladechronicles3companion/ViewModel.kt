package com.example.xenobladechronicles3companion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModel : ViewModel() {
    private val _response = MutableLiveData<List<Monster>>()
    val response : LiveData<List<Monster>>
        get() = _response

    fun getMonsters() {
        val request = MonsterAPI.monsterAPI.getMonsters()
        request.enqueue(object: Callback<MonstersResponse> {
            override fun onFailure(call: Call<MonstersResponse>, t: Throwable) {
                Log.d("RESPONSE", "Failure " + t.message)
            }

            override fun onResponse(
                call: Call<MonstersResponse>,
                response: Response<MonstersResponse>
            ) {
                val listofMonstersFetched = mutableListOf<Monster>()

                val monsterResponse : MonstersResponse? = response.body()
                val monsterList = monsterResponse?.monsterList ?: listOf()

                for (monster in monsterList) {
                    val name = monster.name
                    val level = monster.level
                    val location = monster.location
                    val region = monster.region
                    val superboss = monster.superboss
                    val articleURL = monster.articleurl
                    val imageURL = monster.imageurl
                    val defeated = false

                    val newMonster = Monster(name, level, location, region, superboss, articleURL, imageURL, defeated)
                    listofMonstersFetched.add(newMonster)
                }
                _response.value = listofMonstersFetched
            }
        })
    }
}