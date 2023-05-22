package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.xenobladechronicles3companion.databinding.FragmentAccoladesBinding

const val TOTAL_MONSTERS = 141
const val TOTAL_SUPERBOSSES = 6
const val TOTAL_SIDE_QUESTS = 197
const val TOTAL_HERO_QUESTS = 41
const val TOTAL_OBJECTIVES = TOTAL_HERO_QUESTS + TOTAL_SIDE_QUESTS + TOTAL_SUPERBOSSES + TOTAL_MONSTERS

class AccoladesFragment : Fragment() {

    var _binding : FragmentAccoladesBinding? = null
    val binding get() = _binding!!
    private val viewModel : ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccoladesBinding.inflate(inflater, container, false)

        val totalCompletion = (viewModel.numOfDefeatedMonsters.value!!  + viewModel.numOfDefeatedSuperbosses.value!! + viewModel.numOfCompletedQuests.value!! + viewModel.numOfCompletedHeroQuests.value!!)

        binding.monstersProgressBar.progress = (viewModel.numOfDefeatedMonsters.value!! / TOTAL_MONSTERS) * 100
        binding.superbossProgressBar.progress = (viewModel.numOfDefeatedSuperbosses.value!! / TOTAL_SUPERBOSSES) * 100
        binding.sideQuestProgressBar.progress = (viewModel.numOfCompletedQuests.value!! / TOTAL_SIDE_QUESTS) * 100
        binding.heroQuestProgressBar.progress = (viewModel.numOfCompletedHeroQuests.value!! / TOTAL_HERO_QUESTS) * 100
        binding.overallCompletionProgressBar.progress = (totalCompletion / TOTAL_OBJECTIVES) * 100

        return binding.root
    }

}