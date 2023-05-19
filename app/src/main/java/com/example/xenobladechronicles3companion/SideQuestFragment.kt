package com.example.xenobladechronicles3companion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.xenobladechronicles3companion.databinding.FragmentSideQuestBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SideQuestFragment : Fragment() {

    private var _binding : FragmentSideQuestBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ViewModel by activityViewModels()
    private var completedQuests : MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideQuestBinding.inflate(inflater, container, false)

        viewModel.sideQuestResponse.observe(viewLifecycleOwner, Observer {
            sideQuestList : List<SideQuest> ->
            val adapter = SideQuestRecyclerViewAdapter(sideQuestList, viewModel, completedQuests)
            binding.sideQuestRecyclerView.adapter = adapter
        })

        viewModel.getCompletedSideQuests()
        viewModel.getSideQuests()

        return binding.root
    }

}