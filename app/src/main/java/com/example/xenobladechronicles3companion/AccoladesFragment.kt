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

    private var _binding : FragmentAccoladesBinding? = null
    private val binding get() = _binding!!
    private val viewModel : ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccoladesBinding.inflate(inflater, container, false)

        viewModel.setNumOfDefeatedMonsters()
        viewModel.setNumOfCompletedSideQuests()

        val totalCompletion = (viewModel.numOfDefeatedMonsters.value!!  + viewModel.numOfDefeatedSuperbosses.value!! + viewModel.numOfCompletedQuests.value!! + viewModel.numOfCompletedHeroQuests.value!!)

        binding.monstersProgressBar.progress = (viewModel.numOfDefeatedMonsters.value!! * 100 / TOTAL_MONSTERS)
        binding.superbossProgressBar.progress = (viewModel.numOfDefeatedSuperbosses.value!! * 100 / TOTAL_SUPERBOSSES)
        binding.sideQuestProgressBar.progress = (viewModel.numOfCompletedQuests.value!! * 100 / TOTAL_SIDE_QUESTS)
        binding.heroQuestProgressBar.progress = (viewModel.numOfCompletedHeroQuests.value!! * 100 / TOTAL_HERO_QUESTS)
        binding.overallCompletionProgressBar.progress = (totalCompletion * 100 / TOTAL_OBJECTIVES)

        return binding.root
    }

}