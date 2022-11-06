package com.test.agrocode_tumaris.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.test.agrocode_tumaris.R
import com.test.agrocode_tumaris.databinding.FragmentSignUpBinding
import com.test.agrocode_tumaris.presentation.other.Extensions.toast

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth : FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignUpBinding.bind(view)
        auth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {

            if (
                binding.etEmailSignUp.text.isNotEmpty() &&
                binding.etNameSignUp.text.isNotEmpty() &&
                binding.etPasswordSignUp.text.isNotEmpty()
            ) {
                createUser(binding.etEmailSignUp.text.toString(),binding.etNameSignUp.text.toString())
            }
        }
        binding.tvNavigateToSignIn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragmentFragment)
        }
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if(task.isSuccessful){
                    requireActivity().toast("New User created")
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_signUpFragment_to_mainFragment)
                }
                else{
                    requireActivity().toast(task.exception!!.localizedMessage)
                }

            }
    }
}