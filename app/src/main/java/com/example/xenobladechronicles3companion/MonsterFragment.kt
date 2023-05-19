package com.example.xenobladechronicles3companion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.xenobladechronicles3companion.databinding.FragmentMonsterBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MonsterFragment : Fragment() {

    private var _binding : FragmentMonsterBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ViewModel by activityViewModels()
    private var defeatedMonsterNames : MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonsterBinding.inflate(inflater, container, false)

        viewModel.monsterResponse.observe(viewLifecycleOwner, Observer {
            monsterList : List<Monster> ->
            val mAdapter = MonsterRecyclerViewAdapter(monsterList, viewModel, defeatedMonsterNames)
            binding.monsterRecyclerView.adapter = mAdapter
        })

        viewModel.getDefeatedMonsters()
        viewModel.getMonsters()

        return binding.root
    }
}
