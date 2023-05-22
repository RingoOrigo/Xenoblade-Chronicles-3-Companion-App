package com.example.xenobladechronicles3companion

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideQuestBinding.inflate(inflater, container, false)

        viewModel.sideQuestResponse.observe(viewLifecycleOwner, Observer {
                sideQuestList : List<SideQuest> ->
            val adapter = SideQuestRecyclerViewAdapter(sideQuestList, viewModel)
            binding.sideQuestRecyclerView.adapter = adapter
        })

        setHasOptionsMenu(true)

        viewModel.getCompletedSideQuests()
        viewModel.getSideQuests()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }

}