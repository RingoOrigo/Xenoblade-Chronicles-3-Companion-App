package com.example.xenobladechronicles3companion

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.getColor
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

        if (currentQuest.reqCharacter == "") {
            binding.requires.isVisible = false
            binding.requiredCharacterName.isVisible = false
        } else {
            binding.requiredCharacterName.text = currentQuest.reqCharacter
        }

        setCompleted(binding.checkBox.isChecked)

        val imageURI = currentQuest.imageURL.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.imageView)

    }

    private fun setCompleted(completed: Boolean) {
        currentQuest.completed = completed
        binding.completedX.isVisible = completed
    }
}