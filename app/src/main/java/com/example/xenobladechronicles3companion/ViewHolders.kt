package com.example.xenobladechronicles3companion

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.getColor
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xenobladechronicles3companion.databinding.ListCharacterLayoutBinding
import com.example.xenobladechronicles3companion.databinding.ListMonsterLayoutBinding
import com.example.xenobladechronicles3companion.databinding.ListQuestLayoutBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class MonsterViewHolder (private val binding : ListMonsterLayoutBinding, private val deviceID : String) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentMonster : Monster
    private lateinit var dbref : DatabaseReference
    private lateinit var defeatedMonsters : MutableList<Monster>

    init {
        binding.root.setOnClickListener {
            val monsterURI = Uri.parse(currentMonster.articleURL)
            val intent = Intent(Intent.ACTION_VIEW, monsterURI)
            itemView.context.startActivity(intent)
        }

        binding.defeatedCheckbox.setOnClickListener {
            dbref = Firebase.database.reference

            //In Theory : Connect to Firebase to upload the monster if it is defeated. Can be checked later.
            if (binding.defeatedCheckbox.isChecked){
                val name = currentMonster.name
                val level = currentMonster.level
                val location = currentMonster.location
                val region = currentMonster.region
                val superboss = currentMonster.superboss
                val articleURL = currentMonster.articleURL
                val imageURL = currentMonster.imageURL
                val defeated = binding.defeatedCheckbox.isChecked

                dbref.child(deviceID).child("defeatedMonsters").push().setValue(Monster(name, level, location, region, superboss, articleURL, imageURL, defeated))
            }

            setDefeated(binding.defeatedCheckbox.isChecked)
        }

    }

    fun bindMonster(monster : Monster) {
        dbref = Firebase.database.reference

        currentMonster = monster

        binding.monsterNameTextView.text = monster.name
        binding.levelNumericTextView.text = monster.level.toString()
        binding.areaTextView.text = monster.region
        binding.locationTextView.text = monster.location

        if (currentMonster.superboss) {
            binding.monsterNameTextView.setTextColor(getColor(this.itemView.context, R.color.flute_red))
        } else {
            binding.monsterNameTextView.setTextColor(R.style.Theme_XenobladeChronicles3Companion)
        }

        //TODO("Make connection with firebase.
        //  If the monster is in the database at this point, it was defeated.
        //  Set currentMonster.defeated to true and then run setDefeated.")

        setDefeated(false)

        val imageURI = currentMonster.imageURL.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.monsterImageView)
    }

    private fun setDefeated(defeated : Boolean) {
        binding.defeatedX.isVisible = defeated
        binding.defeatedCheckbox.isChecked = defeated
    }
}

class SideQuestViewHolder(private val binding : ListQuestLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentQuest : SideQuest

    init {
        binding.root.setOnClickListener {
            val questURI = Uri.parse(currentQuest.articleURL)
            val intent = Intent(Intent.ACTION_VIEW, questURI)
            itemView.context.startActivity(intent)
        }
        binding.checkBox.setOnClickListener {
            setCompleted(binding.checkBox.isChecked)
        }
    }

    fun bindSideQuest(quest : SideQuest) {
        currentQuest = quest

        binding.sideQuestName.text = currentQuest.questName
        binding.regionName.text = currentQuest.region
        binding.locationName.text = currentQuest.location
        binding.recommendedLevelNumeric.text = currentQuest.recLevel.toString()

        setQuestColor(currentQuest)
        setReqCharacter(currentQuest.reqCharacter!!)
        setCompleted(binding.checkBox.isChecked)

        val imageURI = currentQuest.imageURL.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.imageView)

    }

    private fun setReqCharacter (reqChar : String) {
        binding.requiredCharacterName.text = reqChar
        binding.requiredCharacterName.isVisible = (reqChar != "")
        binding.requires.isVisible = (reqChar != "")
    }
    private fun setQuestColor(quest : SideQuest) {
        if (quest.DLC!!) {
            binding.sideQuestName.setTextColor(getColor(this.itemView.context, R.color.dlc_blue))
        } else if (quest.heroQuest!!) {
            binding.sideQuestName.setTextColor(getColor(this.itemView.context, R.color.agnian_gold))
        } else binding.sideQuestName.setTextColor(R.style.Theme_XenobladeChronicles3Companion)
    }

    private fun setCompleted(completed: Boolean) {
        currentQuest.completed = completed
        binding.completedX.isVisible = completed
    }
}

class CharacterViewHolder(private val binding : ListCharacterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
 private lateinit var currentCharacter : Character

 init {
     binding.root.setOnClickListener {
         val characterURI = Uri.parse(currentCharacter.articleURL)
         val intent = Intent(Intent.ACTION_VIEW, characterURI)
         itemView.context.startActivity(intent)
     }
 }

 fun bindCharacter(character : Character) {
     currentCharacter = character

     binding.characterNameText.text = currentCharacter.name
     binding.classText.text = currentCharacter.startingClass

     setPlayable(currentCharacter)
     showIcons(currentCharacter)
     setColours(currentCharacter)

     val imageURI = currentCharacter.imageURL.toUri().buildUpon().scheme("https").build()
     Glide.with(itemView.context).load(imageURI).into(binding.characterImage)
 }
    private fun showIcons(char : Character) {
        binding.agnianSymbol.isGone = char.kevesi!!
        binding.kevesiSymbol.isGone = char.agnian!! // I know it's weird, but it's basically just:
        binding.moebiusLogo.isGone = char.ouroboros!! //If Agnian, do not show Kevesi Symbol
        binding.ouroborosLogo.isGone = char.moebius!!
    }
    private fun setPlayable(char : Character) {
        if (char.playable!!) {
            binding.playableText.text = itemView.context.resources.getString(R.string.party_member)
        } else if (char.hero!!) {
            binding.playableText.text = itemView.context.resources.getString(R.string.hero)
        } else binding.playableText.text = itemView.context.resources.getString(R.string.antagonist)
    }

    private fun setColours(char : Character) {
        if (char.DLC!!) {
            binding.characterNameText.setTextColor(getColor(this.itemView.context, R.color.dlc_blue))
        } else if (char.hero!!) {
            binding.characterNameText.setTextColor(getColor(this.itemView.context, R.color.agnian_gold))
        } else binding.characterNameText.setTextColor(R.style.Theme_XenobladeChronicles3Companion)
    }
}