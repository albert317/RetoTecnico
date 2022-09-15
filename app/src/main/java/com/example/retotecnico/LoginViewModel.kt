package com.example.retotecnico

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class LoginViewModel() : ViewModel() {

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    fun login(user: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value?.copy(loading = true)
            withContext(Dispatchers.IO) { delay(1000) }
            if (validateUser(user) && validatePassword(password)) {
                _state.value = _state.value?.copy(loading = false, statusLogin = true, error = null)
            } else {
                _state.value = _state.value?.copy(
                    loading = false,
                    statusLogin = false,
                    error = "Error en usuario y/o contraseña"
                )
            }
        }
    }

    //valida por que el usuario tiene mas de 6 y menos de 20 caracteres
    private fun validateUser(user: String) = user.length in 6..20

    //el password tiene por lo menos un caracter minuscula,mayuscula, numerico, caracter especial y minimo 8 digitos
    private fun validatePassword(password: String): Boolean {
        val specialPattern: Pattern = Pattern.compile(REGEX_PASSWORD)
        val hasSpecial = specialPattern.matcher(password)
        return hasSpecial.find()
    }

    data class UiState(
        val loading: Boolean = false,
        val statusLogin: Boolean = false,
        val error: String? = null
    )
}