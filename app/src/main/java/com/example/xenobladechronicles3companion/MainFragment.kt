package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
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

        return binding.root
    }
    
    fun setNavClickListeners() {
        binding.monstersButton.setOnClickListener {
            binding.root.findNavController().navigate(MainFragmentDirections.actionMainFragmentToMonsterFragment())
        }
    }
}