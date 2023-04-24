package com.example.xenobladechronicles3companion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xenobladechronicles3companion.databinding.FragmentMonsterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

class MonsterFragment : Fragment() {

    private var _binding : FragmentMonsterBinding? = null
    private val binding get() = _binding!!

    lateinit var dbref : DatabaseReference

    val monsters = listOf(
        Monster("Aeing Moramora", 26, "Pentelas Region", "Urayan Tunnels", false, "false", "what", false),
        Monster("Budding Francis",  32, "Pentelas Region", "Great Cotte Falls", true, "false", "what", false),
        Monster("Migratory Circe",  34, "Aetia Region", "Millick Meadows", true, "true", "what", false),
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonsterBinding.inflate(inflater, container, false)

        dbref = Firebase.database.reference

        binding.monsterRecyclerView.adapter = MonsterRecyclerViewAdapter(monsters)

        return binding.root
    }

    fun getMonsterList() {
        TODO("Have moshi process everything here and return a list of Monster s to be used in the RecyclerView")
    }
}
