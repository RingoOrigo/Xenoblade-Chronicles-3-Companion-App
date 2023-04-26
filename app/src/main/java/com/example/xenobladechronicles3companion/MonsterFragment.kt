package com.example.xenobladechronicles3companion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
    private val viewModel : MonsterViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonsterBinding.inflate(inflater, container, false)

        viewModel.response.observe(viewLifecycleOwner, Observer {
            monsterList : List<Monster> ->
            val mAdapter = MonsterRecyclerViewAdapter(monsterList)
            binding.monsterRecyclerView.adapter = mAdapter
        })

        viewModel.getMonsters()

        dbref = Firebase.database.reference

        return binding.root
    }
}
