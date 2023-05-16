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
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase

class MonsterFragment : Fragment() {

    private var _binding : FragmentMonsterBinding? = null
    private val binding get() = _binding!!

    lateinit var dbref : DatabaseReference
    private val viewModel : ViewModel by activityViewModels()
    private lateinit var deviceID : String
    private lateinit var defeatedMonsterNames : MutableList<String>

    init { //Run as soon as fragment is initialized, this will allow dbref to pull data.
        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                deviceID = task.result //Generate unique installation-specific, ID for the user. Will save data between sessions as long as app isn't reinstalled. Persists past updates
            } else {
                Log.e("Installations", "Unable to get Installation ID")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonsterBinding.inflate(inflater, container, false)

        dbref = Firebase.database.reference
        defeatedMonsterNames = mutableListOf()

        dbref.child(deviceID).addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allEntries = snapshot.children
                var numOfMonstersAdded = 0

                for (allMonsters in allEntries) {
                    for (singleMonsterEntry in allMonsters.children) {
                        numOfMonstersAdded++

                        val monsterName = singleMonsterEntry.toString()

                        defeatedMonsterNames.add(monsterName)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }

        })

        viewModel.monsterResponse.observe(viewLifecycleOwner, Observer {
            monsterList : List<Monster> ->

            val mAdapter = MonsterRecyclerViewAdapter(monsterList, deviceID, defeatedMonsterNames)
            binding.monsterRecyclerView.adapter = mAdapter
        })

        viewModel.getMonsters()

        return binding.root
    }
}
