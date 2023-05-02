package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.xenobladechronicles3companion.databinding.FragmentSideQuestBinding

class SideQuestFragment : Fragment() {

    private var _binding : FragmentSideQuestBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideQuestBinding.inflate(inflater, container, false)

        viewModel.sideQuestResponse.observe(viewLifecycleOwner, Observer {
            sideQuestList : List<SideQuest> ->
            val adapter = SideQuestRecyclerViewAdapter(sideQuestList)
            binding.sideQuestRecyclerView.adapter = adapter
        })

        viewModel.getSideQuests()

        return binding.root
    }

}