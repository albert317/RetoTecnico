package com.example.retotecnico

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.retotecnico.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding = FragmentLoginBinding.bind(view)
        configViewModel()
        binding.btnGo.setOnClickListener {
            viewModel.login(binding.txtUser.text.toString(), binding.textPassowrd.text.toString())
        }
    }

    private fun configViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                updateUI(it)
            }
        }
    }

    private fun goListMovies() {
        val action = LoginFragmentDirections.actionLoginFragmentToListMoviesFragment()
        navController.navigate(action)
    }

    private fun updateUI(state: LoginViewModel.UiState) {
        binding.frameProgress.isVisible = state.loading
        binding.btnGo.isEnabled = !state.loading
        if (state.statusLogin) {
            goListMovies()
        }
        if (state.error != null) {
            binding.message.text = state.error
        }
    }
}