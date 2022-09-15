package com.example.retotecnico

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.retotecnico.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var navController: NavController

    private lateinit var binding: FragmentLoginBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding = FragmentLoginBinding.bind(view)
        binding.btnGo.setOnClickListener {
            goListMovies()
        }
    }

    private fun goListMovies() {
        val action = LoginFragmentDirections.actionLoginFragmentToListMoviesFragment()
        navController.navigate(action)
    }

}