package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xenobladechronicles3companion.databinding.FragmentMonsterBinding

class MonsterFragment : Fragment() {

    private var _binding : FragmentMonsterBinding? = null
    private val binding get() = _binding!!

    val monsters = listOf(
        Monster("Aeing Moramora", 26, "Pentelas Region", "Urayan Tunnels", false),
        Monster("Budding Francis",  32, "Pentelas Region", "Great Cotte Falls", true),
        Monster("Migratory Circe",  34, "Aetia Region", "Millick Meadows", true),
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonsterBinding.inflate(inflater, container, false)

        binding.monsterRecyclerView.adapter = MonsterAdapter(monsters)

        return binding.root
    }
}