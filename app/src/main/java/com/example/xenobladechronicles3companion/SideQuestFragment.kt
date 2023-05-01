package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xenobladechronicles3companion.databinding.FragmentSideQuestBinding

class SideQuestFragment : Fragment() {

    private var _binding : FragmentSideQuestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideQuestBinding.inflate(inflater, container, false)

        return binding.root
    }

}