package com.example.gymgoproject.ui.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.gymgoproject.ui.screens.SignInScreen
import com.example.gymgoproject.ui.viewmodels.SignInViewModel
import org.koin.androidx.compose.koinViewModel

const val signInRoute: String = "signIn"

fun NavGraphBuilder.signInScreen(
    onNavigateToSignUp: () -> Unit
) {
    composable(signInRoute) {
        val viewModel = koinViewModel<SignInViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        LaunchedEffect(uiState.isAuthenticated) {
            if (uiState.isAuthenticated) {
                Log.i("Response", "Navegue para a tela principal")
            }
        }
        SignInScreen(
            uiState = uiState,
            onSignInClick = {
                viewModel.authenticate()
            },
            onSignUpClick = onNavigateToSignUp
        )
    }
}

fun NavHostController.navigateToSignIn(
    navOptions: NavOptions? = null

) {
    navigate(signInRoute, navOptions)

}