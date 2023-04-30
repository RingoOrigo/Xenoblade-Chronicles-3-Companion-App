package com.example.xenobladechronicles3companion

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.getColor
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xenobladechronicles3companion.databinding.ListMonsterLayoutBinding

class MonsterViewHolder (private val binding : ListMonsterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentMonster : Monster

    init {
        binding.root.setOnClickListener {
            val monsterURI = Uri.parse(currentMonster.articleurl)
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

        val imageURI = currentMonster.imageurl.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.monsterImageView)
    }

    private fun setDefeated(defeated : Boolean) {
        if (defeated) {
            binding.defeatedX.isVisible = true
            binding.defeatedCheckbox.isChecked = true
        } else {
            binding.defeatedX.isVisible = false
            binding.defeatedCheckbox.isChecked = false
        }
    }
}

//Make class SideQuestViewHolder