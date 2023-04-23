package com.example.xenobladechronicles3companion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xenobladechronicles3companion.databinding.ListMonsterLayoutBinding
import com.squareup.moshi.FromJson

class MonsterAdapter (val monsterList : List<Monster>) : RecyclerView.Adapter<MonsterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val binding = ListMonsterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MonsterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return monsterList.size
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val currentMonster = monsterList[position]
        holder.bindMonster(currentMonster)
    }
}

class MonsterJsonAdapter {
    @FromJson
    fun monsterFromJson(monsterJson : MonsterJson) : Monster {
        return Monster (name = monsterJson.name,
            level = monsterJson.level,
            location = monsterJson.location,
            region = monsterJson.region,
            superboss = monsterJson.superboss ?: false,
            articleURL = monsterJson.articleURL,
            imageURL = monsterJson.imageURL,
            defeated = false
                )
    }
}