package com.example.gymgoproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymgoproject.ui.screens.SignInScreen
import com.example.gymgoproject.ui.states.SignInUiState
import com.example.gymgoproject.ui.theme.GymgoProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymgoProjectTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "signIn"
                ) {
                    composable("signIn") {
                        SignInScreen(uiState = SignInUiState())
                    }
                }
            }
        }
    }
}