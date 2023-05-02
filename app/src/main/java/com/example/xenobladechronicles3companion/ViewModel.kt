package com.example.xenobladechronicles3companion

import android.util.Log
import android.view.WindowInsets
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModel : ViewModel() {
    private val _monsterResponse = MutableLiveData<List<Monster>>()
    val monsterResponse: LiveData<List<Monster>>
        get() = _monsterResponse

    private val _sideQuestResponse = MutableLiveData<List<SideQuest>>()
    val sideQuestResponse: LiveData<List<SideQuest>>
        get() = _sideQuestResponse

    fun getMonsters() {
        val request = MonsterAPI.monsterAPI.getMonsters()
        request.enqueue(object : Callback<MonstersResponse> {
            override fun onFailure(call: Call<MonstersResponse>, t: Throwable) {
                Log.d("RESPONSE", "Failure " + t.message)
            }

            override fun onResponse(
                call: Call<MonstersResponse>,
                response: Response<MonstersResponse>
            ) {
                val listofMonstersFetched = mutableListOf<Monster>()

                val monsterResponse: MonstersResponse? = response.body()
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
                _monsterResponse.value = listofMonstersFetched
            }
        })
    }

    fun getSideQuests() {
        val request = SideQuestAPI.sideQuestAPI.getSideQuests()

        request.enqueue(object : Callback<SideQuestResponse> {
            override fun onFailure(call: Call<SideQuestResponse>, t: Throwable) {
                Log.d("RESPONSE", "Failure " + t.message)
            }

            override fun onResponse(
                call: Call<SideQuestResponse>,
                response: Response<SideQuestResponse>
            ) {
                val listOfQuestsFetched = mutableListOf<SideQuest>()

                val sideQuestResponse: SideQuestResponse? = response.body()
                val sideQuestList = sideQuestResponse?.sideQuestList ?: listOf()

                for (quest in sideQuestList) {
                    val name = quest.questName
                    val recLevel = quest.recLevel
                    val reqCharacter = quest.reqCharacter ?: ""
                    val region = quest.region
                    val location = quest.location
                    val DLC = quest.dlc ?: false
                    val heroQuest = quest.heroQuest ?: false
                    val imageURL = quest.imageURL
                    val articleURL = quest.articleURL
                    val completed = false

                    val newQuest = SideQuest(name, recLevel, reqCharacter, region, location, DLC, heroQuest, imageURL, articleURL, completed)
                    listOfQuestsFetched.add(newQuest)
                }
                _sideQuestResponse.value = listOfQuestsFetched
            }
        })
    }
}