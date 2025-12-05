package com.example.cameratest.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.navigation.HomeScreenRoute
import com.example.cameratest.navigation.LoginScreenRoute
import com.example.cameratest.navigation.RegisterScreenRoute
import com.example.cameratest.ui.screens.auth.components.AuthActionButton
import com.example.cameratest.ui.screens.auth.components.HeaderAuth
import com.example.cameratest.ui.screens.components.AuralisPasswordField
import com.example.cameratest.ui.screens.components.AuralisTextField
import com.example.cameratest.ui.theme.CameraTestTheme
import com.example.cameratest.ui.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val colorScheme = MaterialTheme.colorScheme
    val authViewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(authViewModel.isLogged) {
        if (authViewModel.isLogged){
            navController.navigate(HomeScreenRoute){
                popUpTo(LoginScreenRoute){
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ================= HEADER =================
            HeaderAuth(
                title = "Welcome to",
                subtitle = "Auralis"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ================= FORM =================
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // EMAIL
                AuralisTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    imeAction = ImeAction.Next
                )


                Spacer(modifier = Modifier.height(16.dp))

                // PASSWORD
                AuralisPasswordField(
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    imeAction = ImeAction.Send,
                    keyboardActions = KeyboardActions(
                        onSend = {
                            if (email.isBlank() || password.isBlank()) return@KeyboardActions

                            authViewModel.login(
                                email = email,
                                password = password
                            )
                        }
                    )
                )


                Spacer(modifier = Modifier.height(24.dp))

                // BOTÓN SIGN IN
                AuthActionButton(
                    title = "Sign In",
                    onClick = {
                        if (email.isBlank() || password.isBlank()) return@AuthActionButton

                        authViewModel.login(
                            email = email,
                            password = password
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // TEXTO PARA REGISTRO
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Don’t have an account? ",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Sign up",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.navigate(RegisterScreenRoute)
                        }
                    )
                }
            }
        }
    }
}


// PREVIEW
@Preview(showBackground = true)
@Composable
fun LoginScreenPreviewLight() {
    CameraTestTheme(darkTheme = false) {
        LoginScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreviewDark() {
    CameraTestTheme(darkTheme = true) {
        LoginScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}