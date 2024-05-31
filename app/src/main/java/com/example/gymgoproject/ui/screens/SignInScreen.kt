package com.example.gymgoproject.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymgoproject.R
import com.example.gymgoproject.ui.states.SignInUiState
import com.example.gymgoproject.ui.theme.GymgoProjectTheme

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val textFieldModifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(8.dp)
        OutlinedTextField(
            modifier = textFieldModifier,
            value = uiState.user,
            onValueChange = uiState.onUserChange,
            shape = RoundedCornerShape(25),
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Icone do usuario",
                )
            },
            label = {
                Text("Usuário")
            }
        )
        OutlinedTextField(
            value = uiState.password,
            onValueChange = uiState.onPasswordChange,
            textFieldModifier,
            shape = RoundedCornerShape(25),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Icone da senha"
                )
            },
            label = { Text("Senha") },
            trailingIcon = {
                val trailingIconModifier = Modifier.clickable {
                    uiState.onTogglePasswordVisibility()
                }
                when (uiState.isShowPassword) {
                    true -> {
                        Icon(
                            painter = painterResource(id = R.drawable.visibility),
                            contentDescription = "Icone de visivel",
                            trailingIconModifier
                        )
                    }

                    else -> Icon(
                        painter = painterResource(id = R.drawable.visibility_off),
                        contentDescription = "Icone de invisivel",
                        trailingIconModifier
                    )
                }
            },
            visualTransformation = when (uiState.isShowPassword) {
                false -> PasswordVisualTransformation()
                true -> VisualTransformation.None
            }
        )
        Button(
            onClick = onSignInClick,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(8.dp)
        ) {
            Text("Entrar")
        }
        TextButton(
            onClick = onSignUpClick,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(8.dp)
        ) {
            Text("Cadastrar")
        }

    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SignInScreenPreview() {
    GymgoProjectTheme {
        SignInScreen(
            uiState = SignInUiState(),
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}