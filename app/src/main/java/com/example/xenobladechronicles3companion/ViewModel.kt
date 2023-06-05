package com.example.xenobladechronicles3companion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
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

    private val _characterResponse = MutableLiveData<List<Character>>()
    val characterResponse: LiveData<List<Character>>
        get() = _characterResponse

    private val _deviceID = MutableLiveData<String>()
    val deviceID : LiveData<String>
        get() = _deviceID

    private val _defeatedMonsterNames = MutableLiveData<List<String>>()
    val defeatedMonsterNames : LiveData<List<String>>
        get() = _defeatedMonsterNames

    private val _completedSideQuestNames = MutableLiveData<List<String>>()
    val completedSideQuestNames : LiveData<List<String>>
        get() = _completedSideQuestNames

    private val _numOfDefeatedMonsters = MutableLiveData(0)
    val numOfDefeatedMonsters : LiveData<Int>
        get() = _numOfDefeatedMonsters

    private val _numOfDefeatedSuperbosses = MutableLiveData(0)
    val numOfDefeatedSuperbosses : LiveData<Int>
        get() = _numOfDefeatedSuperbosses

    private val _numOfCompletedQuests = MutableLiveData(0)
    val numOfCompletedQuests : LiveData<Int>
        get() = _numOfCompletedQuests

    private val _numOfCompletedHeroQuests = MutableLiveData(0)
    val numOfCompletedHeroQuests : LiveData<Int>
        get() = _numOfCompletedHeroQuests



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
                    val articleURL = monster.articleURL
                    val imageURL = monster.imageURL
                    val defeated = (monster.name in (defeatedMonsterNames.value ?: mutableListOf()))

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
                    val DLC = quest.DLC ?: false
                    val heroQuest = quest.heroQuest ?: false
                    val imageURL = quest.imageURL
                    val articleURL = quest.articleURL
                    val completed = (quest.questName in (completedSideQuestNames.value ?: mutableListOf()))

                    val newQuest = SideQuest(name, recLevel, reqCharacter, region, location, DLC, heroQuest, imageURL, articleURL, completed)
                    listOfQuestsFetched.add(newQuest)
                }
                _sideQuestResponse.value = listOfQuestsFetched
            }
        })
    }

    fun getCharacters() {
        val request = CharacterAPI.characterAPI.getCharacters()

        request.enqueue(object : Callback<CharacterResponse> {
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.d("RESPONSE", "FAILURE: " + t.message)
            }

            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                val listOfCharactersFetched = mutableListOf<Character>()

                val characterResponse : CharacterResponse? = response.body()
                val characterList = characterResponse?.characterList ?: listOf()

                for (character in characterList) {
                    val name = character.name
                    val kevesi = character.kevesi?: false
                    val agnian = character.agnian?: false
                    val city = character.city?: false
                    val moebius = character.moebius?: false
                    val ouroboros = character.ouroboros?: false
                    val startingClass = character.startingClass
                    val hero = character.hero?: false
                    val playable = character.playable?: false
                    val DLC = character.DLC?: false
                    val articleURL = character.articleURL
                    val imageURL = character.imageURL

                    val newCharacter = Character(name, kevesi, agnian, city, moebius, ouroboros, startingClass, hero, playable, DLC, articleURL, imageURL)
                    listOfCharactersFetched.add(newCharacter)
                }
                _characterResponse.value = listOfCharactersFetched
            }
        })
    }

    fun generateID() {
        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _deviceID.value = task.result

                getMonsters()
                getSideQuests()

                getDefeatedMonsters()
                getCompletedSideQuests()
            } else {
                Log.e("Installations", "Unable to get Installation ID")
            }
        }
    }

    private fun getDefeatedMonsters() {
        val dbref = Firebase.database.reference
        val defeatedMonsters : MutableList<String> = mutableListOf()

        dbref.child(deviceID.value!!).child("defeatedMonsters").addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allEntries = snapshot.children

                for (entry in allEntries) {
                    defeatedMonsters.add(entry.key.toString())
                }
                    _defeatedMonsterNames.value = defeatedMonsters
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }
        })
    }

    private fun getCompletedSideQuests() {
        val dbref = Firebase.database.reference
        val completedQuests: MutableList<String> = mutableListOf()

        dbref.child(deviceID.value!!).child("completedSideQuests")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val allEntries = snapshot.children
                    for (entry in allEntries) {
                        completedQuests.add(entry.key.toString())
                    }

                    _completedSideQuestNames.value = completedQuests
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("MainFragment", "Failed to read value.", error.toException())
                }
            })
    }

    fun setNumOfCompletedSideQuests() {
        var completedQuestNum = 0
        var completedHeroQuestNum = 0

        for (quest in _sideQuestResponse.value!!) {
            if (quest.questName in _completedSideQuestNames.value!!) {
                completedQuestNum++
                if (quest.heroQuest == true) completedHeroQuestNum++
            }
        }

        _numOfCompletedQuests.value = completedQuestNum
        _numOfCompletedHeroQuests.value = completedHeroQuestNum
    }

    fun setNumOfDefeatedMonsters() {
        var defeatedMonstersNum = 0
        var defeatedSuperbossesNum = 0

        for (monster in _monsterResponse.value!!) {
            if (monster.name in _defeatedMonsterNames.value!!) {
                defeatedMonstersNum++
                if (monster.superboss) defeatedSuperbossesNum++
            }
        }
        _numOfDefeatedMonsters.value = defeatedMonstersNum
        _numOfDefeatedSuperbosses.value = defeatedSuperbossesNum
    }
}