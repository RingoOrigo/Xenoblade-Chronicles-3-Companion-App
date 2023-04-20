package com.example.xenobladechronicles3companion

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.xenobladechronicles3companion.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,  container, false)
        
        setNavClickListeners()
        setHasOptionsMenu(true)

        return binding.root
    }
    
    fun setNavClickListeners() {
        binding.uniqueMonstersButton.setOnClickListener {
            binding.root.findNavController().navigate(MainFragmentDirections.actionMainFragmentToMonsterFragment())
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