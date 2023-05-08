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

class MonsterViewHolder (private val binding : ListMonsterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentMonster : Monster

    init {
        binding.root.setOnClickListener {
            val monsterURI = Uri.parse(currentMonster.articleURL)
            val intent = Intent(Intent.ACTION_VIEW, monsterURI)
            itemView.context.startActivity(intent)
        }

        binding.defeatedCheckbox.setOnClickListener {
            setDefeated(binding.defeatedCheckbox.isChecked)
        }

    }

    fun bindMonster(monster : Monster) {
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

        setDefeated(currentMonster.defeated)

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

 fun bindCharacter(character : Character) {
     currentCharacter = character

     binding.characterNameText.text = currentCharacter.name
     binding.classText.text = currentCharacter.startingClass

     setPlayable(currentCharacter)
     showIcons(currentCharacter)

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
            binding.playableText.text = "Party Member"
        } else if (char.hero!!) {
            binding.playableText.text = "Hero"
        } else binding.playableText.text = "Antagonist"
    }
}