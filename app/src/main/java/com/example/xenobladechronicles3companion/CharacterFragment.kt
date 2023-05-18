package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.xenobladechronicles3companion.databinding.FragmentCharacterBinding

class CharacterFragment : Fragment() {

    private var _binding : FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(layoutInflater, container, false)

        viewModel.characterResponse.observe(viewLifecycleOwner, Observer {
                characterList : List<Character> ->
            val adapter = CharacterRecyclerViewAdapter(characterList)
            binding.characterRecyclerView.adapter = adapter
        })

        viewModel.getCharacters()

        return binding.root
    }
}