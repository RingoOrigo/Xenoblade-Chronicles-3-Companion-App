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
    private lateinit var dbref : DatabaseReference
    private lateinit var completedQuests : MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideQuestBinding.inflate(inflater, container, false)

        dbref = Firebase.database.reference
        completedQuests = mutableListOf()

        dbref.child(viewModel.deviceID.value!!).child("completedSideQuests").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allEntries = snapshot.children
                var numOfQuestsAdded = 0

                for (entry in allEntries) {
                        numOfQuestsAdded++
                        completedQuests.add(entry.key.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }

        })

        viewModel.sideQuestResponse.observe(viewLifecycleOwner, Observer {
            sideQuestList : List<SideQuest> ->
            val adapter = SideQuestRecyclerViewAdapter(sideQuestList, viewModel, completedQuests)
            binding.sideQuestRecyclerView.adapter = adapter
        })

        viewModel.getSideQuests()

        return binding.root
    }

}