package com.example.xenobladechronicles3companion

import android.content.Intent
import android.net.Uri
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



class MonsterViewHolder (private val binding : ListMonsterLayoutBinding, private val viewModel: ViewModel, private val defeatedMonsterNames : MutableList<String>) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentMonster : Monster
    private lateinit var dbref : DatabaseReference

    init {

        binding.root.setOnClickListener {
            val monsterURI = Uri.parse(currentMonster.articleURL)
            val intent = Intent(Intent.ACTION_VIEW, monsterURI)
            itemView.context.startActivity(intent)
        }

        binding.defeatedCheckbox.setOnClickListener {
            dbref = Firebase.database.reference
            val path = dbref.child(viewModel.deviceID.value!!).child("defeatedMonsters").child(currentMonster.name)

            currentMonster.defeated = binding.defeatedCheckbox.isChecked
            if (currentMonster.defeated){
                viewModel.numOfDefeatedMonsters.value!!.plus(1)

                if (currentMonster.superboss) {
                    viewModel.numOfDefeatedSuperbosses.value!!.plus(1)
                }
                path.push().setValue(currentMonster) //Uploads defeated monsters to firebase.
            } else {
                viewModel.numOfDefeatedMonsters.value!!.minus(1)

                if (currentMonster.superboss) {
                    viewModel.numOfDefeatedSuperbosses.value!!.minus(1)
                }
                path.removeValue() //Removes monsters from firebase if not defeated.
            }

            setDefeated(binding.defeatedCheckbox.isChecked)
        }

    }

    fun bindMonster(monster : Monster) {
        currentMonster = monster

        binding.monsterNameTextView.text = monster.name
        binding.levelNumericTextView.text = monster.level.toString()
        binding.areaTextView.text = monster.region
        binding.locationTextView.text = monster.location

        if(currentMonster.name in defeatedMonsterNames) {
            binding.defeatedCheckbox.isChecked = true
            setDefeated(true)
        } else {
            setDefeated(false)
        }

        if (currentMonster.superboss) {
            binding.monsterNameTextView.setTextColor(getColor(this.itemView.context, R.color.flute_red))
        } else {
            binding.monsterNameTextView.setTextColor(R.style.Theme_XenobladeChronicles3Companion)
        }

        val imageURI = currentMonster.imageURL.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.monsterImageView)
    }

    private fun setDefeated(defeated : Boolean) {
        binding.defeatedX.isVisible = defeated
        binding.defeatedCheckbox.isChecked = defeated
    }
}

class SideQuestViewHolder(private val binding : ListQuestLayoutBinding, val viewModel: ViewModel, private val completedQuests : MutableList<String>) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentQuest : SideQuest
    private lateinit var dbref : DatabaseReference

    init {

        binding.root.setOnClickListener {
            val questURI = Uri.parse(currentQuest.articleURL)
            val intent = Intent(Intent.ACTION_VIEW, questURI)
            itemView.context.startActivity(intent)
        }

        binding.completedCheckBox.setOnClickListener {
            dbref = Firebase.database.reference

            val path = dbref.child(viewModel.deviceID.value!!).child("completedSideQuests").child(currentQuest.questName)

            currentQuest.completed = binding.completedCheckBox.isChecked
            if (currentQuest.completed){
                viewModel.numOfCompletedQuests.value!!.plus(1)

                if (currentQuest.heroQuest!!) {
                    viewModel.numOfCompletedHeroQuests.value!!.plus(1)
                }
                path.push().setValue(currentQuest) //Uploads defeated monsters to firebase.
            } else {
                viewModel.numOfCompletedQuests.value!!.minus(1)

                if (currentQuest.heroQuest!!) {
                    viewModel.numOfCompletedHeroQuests.value!!.minus(1)
                }
                path.removeValue() //Removes monsters from firebase if not defeated.
            }

            setCompleted(binding.completedCheckBox.isChecked)
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

        if (currentQuest.questName in completedQuests) {
            binding.completedCheckBox.isChecked = true
            setCompleted(true)
        } else {
            setCompleted(false)
        }

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