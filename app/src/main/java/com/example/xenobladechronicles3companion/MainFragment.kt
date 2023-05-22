package com.example.xenobladechronicles3companion

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.xenobladechronicles3companion.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,  container, false)
        
        setNavClickListeners()
        setHasOptionsMenu(true)

        viewModel.generateID()

        return binding.root
    }
    
    fun setNavClickListeners() {
        binding.uniqueMonstersButton.setOnClickListener {
            binding.root.findNavController().navigate(MainFragmentDirections.actionMainFragmentToMonsterFragment())
        }

        binding.sideQuestsButton.setOnClickListener {
            binding.root.findNavController().navigate(MainFragmentDirections.actionMainFragmentToSideQuestFragment())
        }

        binding.charactersButton.setOnClickListener {
            binding.root.findNavController().navigate(MainFragmentDirections.actionMainFragmentToCharacterFragment())
        }

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