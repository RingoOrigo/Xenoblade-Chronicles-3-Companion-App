package com.example.xenobladechronicles3companion

import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
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
    }

    fun bindMonster(monster : Monster) {
        currentMonster = monster

        binding.monsterNameTextView.text = monster.name
        binding.levelNumericTextView.text = monster.level.toString()
        binding.areaTextView.text = monster.region
        binding.locationTextView.text = monster.location

        val imageURI = currentMonster.imageurl.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.monsterImageView)
    }
}