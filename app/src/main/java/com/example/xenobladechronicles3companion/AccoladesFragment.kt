package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.xenobladechronicles3companion.databinding.FragmentAccoladesBinding


class AccoladesFragment : Fragment() {

    var _binding : FragmentAccoladesBinding? = null
    val binding get() = _binding!!
    private val viewModel : ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccoladesBinding.inflate(inflater, container, false)

        binding.monstersProgressBar.setProgress(viewModel.numOfDefeatedMonsters.value!! / )

        return binding.root
    }

}