package com.example.xenobladechronicles3companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.xenobladechronicles3companion.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel.setAuth()

        binding.signUpButton.setOnClickListener {
            createAccount(binding.emailInputField.text.toString(), binding.passwordInputField.text.toString())
        }

        binding.signInButton.setOnClickListener {
            signIn(binding.emailInputField.text.toString(), binding.passwordInputField.text.toString())
        }

        return binding.root
    }

    private fun createAccount (email : String, password : String) {
        viewModel.auth.value!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                viewModel.setUserID()
                binding.root.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
            } else {
                Toast.makeText(requireActivity(), R.string.account_create_failure, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn (email : String, password : String) {
        viewModel.auth.value!!.signInWithEmailAndPassword(email, password).addOnCompleteListener() {task ->
             if (task.isSuccessful) {
                 viewModel.setUserID()
                 binding.root.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
             } else {
                 Toast.makeText(requireActivity(), R.string.sign_in_failure, Toast.LENGTH_SHORT).show()
             }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}