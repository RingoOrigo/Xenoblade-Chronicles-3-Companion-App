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
        Monster("Aeing Moramora", "Aeing_Moramora", 26, "Pentelas Region", "Urayan Tunnels", false, false),
        Monster("Budding Francis", "Budding_Francis", 32, "Pentelas Region", "Great Cotte Falls", true, false),
        Monster("Migratory Circe", "Migratory_Circe", 34, "Aetia Region", "Millick Meadows", true, false),
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