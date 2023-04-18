package com.example.xenobladechronicles3companion

import androidx.recyclerview.widget.RecyclerView
import com.example.xenobladechronicles3companion.databinding.ListMonsterLayoutBinding

class MonsterViewHolder (private val binding : ListMonsterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentMonster : Monster

    fun bindMonster(monster : Monster) {
        currentMonster = monster

        binding.monsterNameTextView.text = monster.name
        binding.levelNumericTextView.text = monster.level.toString()
        binding.areaTextView.text = monster.area
        binding.locationTextView.text = monster.location

        if (monster.unique) binding.uniqueMonsterWarningImage.setImageResource(R.drawable.unique_monster_icon)
    }
}