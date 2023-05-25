package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.xenobladechronicles3companion.databinding.FragmentAccoladesBinding

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

        setClickListeners()

        val totalCompletion = (viewModel.numOfDefeatedMonsters.value!!  + viewModel.numOfDefeatedSuperbosses.value!! + viewModel.numOfCompletedQuests.value!! + viewModel.numOfCompletedHeroQuests.value!!)
        val totalObjectives = (141 + 7 + 197 + 41)

        binding.monstersProgressBar.progress = (viewModel.numOfDefeatedMonsters.value!! * 100 / 141)
        binding.superbossProgressBar.progress = (viewModel.numOfDefeatedSuperbosses.value!! * 100 / 7)
        binding.sideQuestProgressBar.progress = (viewModel.numOfCompletedQuests.value!! * 100 / 197)
        binding.heroQuestProgressBar.progress = (viewModel.numOfCompletedHeroQuests.value!! * 100 / 41)
        binding.overallCompletionProgressBar.progress = (totalCompletion * 100 / totalObjectives)

        return binding.root
    }
    fun setClickListeners() {
        val progressListener : View.OnClickListener = View.OnClickListener { view ->
            when (view) {
                binding.monstersProgressBar -> Toast.makeText(requireActivity(), binding.monstersProgressBar.progress.toString() + getString(R.string.percent_complete), Toast.LENGTH_SHORT).show()
                binding.superbossProgressBar -> Toast.makeText(requireActivity(), binding.superbossProgressBar.progress.toString() + getString(R.string.percent_complete), Toast.LENGTH_SHORT).show()
                binding.sideQuestProgressBar -> Toast.makeText(requireActivity(), binding.sideQuestProgressBar.progress.toString() + getString(R.string.percent_complete), Toast.LENGTH_SHORT).show()
                binding.heroQuestProgressBar -> Toast.makeText(requireActivity(), binding.heroQuestProgressBar.progress.toString() + getString(R.string.percent_complete), Toast.LENGTH_SHORT).show()
                binding.overallCompletionProgressBar -> Toast.makeText(requireActivity(), binding.overallCompletionProgressBar.progress.toString() + getString(R.string.percent_complete), Toast.LENGTH_SHORT).show()
            }
        }

        binding.monstersProgressBar.setOnClickListener(progressListener)
        binding.superbossProgressBar.setOnClickListener(progressListener)
        binding.sideQuestProgressBar.setOnClickListener(progressListener)
        binding.heroQuestProgressBar.setOnClickListener(progressListener)
        binding.overallCompletionProgressBar.setOnClickListener(progressListener)
    }

}