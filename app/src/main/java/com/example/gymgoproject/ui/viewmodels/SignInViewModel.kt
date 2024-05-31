package com.example.gymgoproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.gymgoproject.repositories.UsersRepository
import com.example.gymgoproject.ui.states.SignInUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel(
    private val repository: UsersRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUserChange = { user ->
                    _uiState.update {
                        it.copy(user = user)
                    }
                },
                onPasswordChange = { password ->
                    _uiState.update {
                        it.copy(password = password)
                    }
                },
                onTogglePasswordVisibility = {
                    _uiState.update {
                        it.copy(isShowPassword = !it.isShowPassword)
                    }
                }
            )
        }
    }

    fun authenticate() {
        with(_uiState.value) {
            _uiState.update {
                it.copy(
                    isAuthenticated = repository.authenticate(
                        user,
                        password
                    )
                )
            }
        }
    }
}