package com.example.gymgoproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gymgoproject.authentication.FirebaseAuthRepository
import com.example.gymgoproject.ui.states.SignUpUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()
    private val _signUpIsSuccessful = MutableSharedFlow<Boolean>()
    val signUpIsSuccessful = _signUpIsSuccessful.asSharedFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { user ->
                    _uiState.update {
                        it.copy(email = user)
                    }
                },
                onPasswordChange = { password ->
                    _uiState.update {
                        it.copy(password = password)
                    }
                },
                onConfirmPasswordChange = { confirmPassword ->
                    _uiState.update {
                        it.copy(confirmPassword = confirmPassword)
                    }
                }
            )
        }
    }


    suspend fun signUp() {
        try {
            firebaseAuthRepository.signUp(
                _uiState.value.email,
                _uiState.value.password
            )
            _signUpIsSuccessful.emit(true)

        } catch (e: Exception) {
            Log.e("SignUpViewModel", "signUp: ", e)
            _uiState.update {
                it.copy(error = "Erro ao cadastrar usuário")
            }
        }

    }
}