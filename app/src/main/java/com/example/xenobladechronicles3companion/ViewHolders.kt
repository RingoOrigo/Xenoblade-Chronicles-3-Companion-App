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


class MonsterViewHolder(
    private val binding: ListMonsterLayoutBinding,
    private val viewModel: ViewModel
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentMonster: Monster
    private lateinit var dbref: DatabaseReference

    init {

        binding.root.setOnClickListener {
            val monsterURI = Uri.parse(currentMonster.articleURL)
            val intent = Intent(Intent.ACTION_VIEW, monsterURI)
            itemView.context.startActivity(intent)
        }

        binding.defeatedCheckbox.setOnClickListener {
            dbref = Firebase.database.reference
            val path = dbref.child(viewModel.userID.value!!).child("defeatedMonsters")
                .child(currentMonster.name)

            currentMonster.defeated = binding.defeatedCheckbox.isChecked

            if (currentMonster.defeated) {
                path.push().setValue(currentMonster) //Uploads defeated monsters to firebase.
            } else {
                path.removeValue() //Removes monsters from firebase if not defeated.
            }

            setDefeated(binding.defeatedCheckbox.isChecked)
        }
    }

    fun bindMonster(monster: Monster) {
        currentMonster = monster

        binding.monsterNameTextView.text = monster.name
        binding.levelNumericTextView.text = monster.level.toString()
        binding.areaTextView.text = monster.region
        binding.locationTextView.text = monster.location

        setDefeated(currentMonster.defeated)

        if (currentMonster.superboss) {
            binding.monsterNameTextView.setTextColor(
                getColor(
                    this.itemView.context,
                    R.color.flute_red
                )
            )
        } else {
            binding.monsterNameTextView.setTextColor(
                getColor(
                    this.itemView.context,
                    R.color.greyple
                )
            )
        }

        val imageURI = currentMonster.imageURL.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.monsterImageView)
    }

    private fun setDefeated(defeated: Boolean) {
        currentMonster.defeated = defeated
        binding.defeatedX.isGone = !defeated
        binding.defeatedCheckbox.isChecked = defeated
    }
}

class SideQuestViewHolder(
    private val binding: ListQuestLayoutBinding,
    private val viewModel: ViewModel
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentQuest: SideQuest
    private lateinit var dbref: DatabaseReference

    init {
        binding.root.setOnClickListener {
            val questURI = Uri.parse(currentQuest.articleURL)
            val intent = Intent(Intent.ACTION_VIEW, questURI)
            itemView.context.startActivity(intent)
        }

        binding.completedCheckBox.setOnClickListener {
            dbref = Firebase.database.reference

            val path = dbref.child(viewModel.userID.value!!).child("completedSideQuests")
                .child(currentQuest.questName)

            currentQuest.completed = binding.completedCheckBox.isChecked

            if (currentQuest.completed) {
                path.push().setValue(currentQuest) //Uploads defeated monsters to firebase.
            } else {
                path.removeValue() //Removes monsters from firebase if not defeated.
            }

            setCompleted(binding.completedCheckBox.isChecked)
        }
    }

    fun bindSideQuest(quest: SideQuest) {
        currentQuest = quest

        binding.sideQuestName.text = currentQuest.questName
        binding.regionName.text = currentQuest.region
        binding.locationName.text = currentQuest.location
        binding.recommendedLevelNumeric.text = currentQuest.recLevel.toString()

        setQuestColor(currentQuest)
        setReqCharacter(currentQuest.reqCharacter!!)
        setCompleted(currentQuest.completed)

        val imageURI = currentQuest.imageURL.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.imageView)
    }

    private fun setReqCharacter(reqChar: String) {
        binding.requiredCharacterName.text = reqChar
        binding.requiredCharacterName.isVisible = (reqChar != "")
        binding.requires.isVisible = (reqChar != "")
    }

    private fun setQuestColor(quest: SideQuest) {
        if (quest.DLC!!) {
            binding.sideQuestName.setTextColor(getColor(this.itemView.context, R.color.dlc_blue))
        } else if (quest.heroQuest!!) {
            binding.sideQuestName.setTextColor(getColor(this.itemView.context, R.color.agnian_gold))
        } else binding.sideQuestName.setTextColor(getColor(this.itemView.context, R.color.greyple))
    }

    private fun setCompleted(completed: Boolean) {
        binding.completedCheckBox.isChecked = completed
        currentQuest.completed = completed
        binding.completedX.isGone = !completed

        if (completed) {
            viewModel.numOfCompletedQuests.value!!.plus(1)

            if (currentQuest.heroQuest!!) {
                viewModel.numOfCompletedHeroQuests.value!!.plus(1)
            }
        } else {
            viewModel.numOfCompletedQuests.value!!.minus(1)

            if (currentQuest.heroQuest!!) {
                viewModel.numOfCompletedHeroQuests.value!!.minus(1)
            }
        }
    }
}

class CharacterViewHolder(private val binding: ListCharacterLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentCharacter: Character

    init {
        binding.root.setOnClickListener {
            val characterURI = Uri.parse(currentCharacter.articleURL)
            val intent = Intent(Intent.ACTION_VIEW, characterURI)
            itemView.context.startActivity(intent)
        }
    }

    fun bindCharacter(character: Character) {
        currentCharacter = character

        binding.characterNameText.text = currentCharacter.name
        binding.classText.text = currentCharacter.startingClass

        setPlayable(currentCharacter)
        showIcons(currentCharacter)
        setColours(currentCharacter)

        val imageURI = currentCharacter.imageURL.toUri().buildUpon().scheme("https").build()
        Glide.with(itemView.context).load(imageURI).into(binding.characterImage)
    }

    private fun showIcons(char: Character) {
        binding.agnianSymbol.isVisible = char.agnian!!
        binding.kevesiSymbol.isVisible = char.kevesi!!
        binding.moebiusLogo.isVisible = char.moebius!!
        binding.ouroborosLogo.isVisible = char.ouroboros!!
        binding.citySymbol.isVisible = char.city!!
        binding.noponSymbol.isVisible = char.nopon!!
    }

    private fun setPlayable(char: Character) {
        if (char.playable!!) {
            binding.playableText.text = itemView.context.resources.getString(R.string.party_member)
        } else if (char.hero!!) {
            binding.playableText.text = itemView.context.resources.getString(R.string.hero)
        } else binding.playableText.text = itemView.context.resources.getString(R.string.antagonist)
    }

    private fun setColours(char: Character) {
        if (char.DLC!!) {
            binding.characterNameText.setTextColor(
                getColor(
                    this.itemView.context,
                    R.color.dlc_blue
                )
            )
        } else if (char.hero!!) {
            binding.characterNameText.setTextColor(
                getColor(
                    this.itemView.context,
                    R.color.agnian_gold
                )
            )
        } else binding.characterNameText.setTextColor(
            getColor(
                this.itemView.context,
                R.color.greyple
            )
        )
    }
}