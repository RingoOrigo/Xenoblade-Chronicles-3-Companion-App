package com.example.xenobladechronicles3companion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xenobladechronicles3companion.databinding.ListCharacterLayoutBinding
import com.example.xenobladechronicles3companion.databinding.ListMonsterLayoutBinding
import com.example.xenobladechronicles3companion.databinding.ListQuestLayoutBinding

class MonsterRecyclerViewAdapter (val monsterList : List<Monster>, val viewModel: ViewModel) : RecyclerView.Adapter<MonsterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val binding =
            ListMonsterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MonsterViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int {
        return monsterList.size
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val currentMonster = monsterList[position]
        holder.bindMonster(currentMonster)
    }
}

class SideQuestRecyclerViewAdapter (val sideQuestList : List<SideQuest>, val viewModel: ViewModel) : RecyclerView.Adapter<SideQuestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SideQuestViewHolder {
        val binding = ListQuestLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SideQuestViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: SideQuestViewHolder, position: Int) {
        val currentQuest = sideQuestList[position]
        holder.bindSideQuest(currentQuest)
    }

    override fun getItemCount(): Int {
        return sideQuestList.size
    }
}

class CharacterRecyclerViewAdapter (val characterList : List<Character>) : RecyclerView.Adapter<CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ListCharacterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentCharacter = characterList[position]
        holder.bindCharacter(currentCharacter)
    }

}