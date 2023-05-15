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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase

class MonsterFragment : Fragment() {

    private var _binding : FragmentMonsterBinding? = null
    private val binding get() = _binding!!

    lateinit var dbref : DatabaseReference
    private val viewModel : ViewModel by activityViewModels()
    private lateinit var deviceID : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonsterBinding.inflate(inflater, container, false)

        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                deviceID = task.result
            } else {
                Log.e("Installations", "Unable to get Installation ID")
            }
        }

        viewModel.monsterResponse.observe(viewLifecycleOwner, Observer {
            monsterList : List<Monster> ->

            val mAdapter = MonsterRecyclerViewAdapter(monsterList, deviceID)
            binding.monsterRecyclerView.adapter = mAdapter
        })

        viewModel.getMonsters()



        dbref = Firebase.database.reference

        return binding.root
    }
}
