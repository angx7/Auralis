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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.ui.screens.auth.components.AuthActionButton
import com.example.cameratest.ui.screens.auth.components.HeaderAuth
import com.example.cameratest.ui.screens.components.AuralisLoadingOverlay
import com.example.cameratest.ui.screens.components.AuralisPasswordField
import com.example.cameratest.ui.screens.components.AuralisTextField
import com.example.cameratest.ui.theme.CameraTestTheme
import com.example.cameratest.ui.viewmodels.EditUserViewModel

@Composable
fun EditUserScreen(
    innerPadding: PaddingValues,
    navController: NavController
) {
    val colorScheme = MaterialTheme.colorScheme
    val viewModel: EditUserViewModel = viewModel()
    val username = viewModel.username
    val email = viewModel.email
    val message = viewModel.message
    val isLoading = viewModel.isLoading
    val updateSuccess = viewModel.updateSuccess

    LaunchedEffect(Unit) {
        viewModel.loadUserProfile()
    }

    LaunchedEffect(updateSuccess) {
        if (updateSuccess) {
            navController.popBackStack()
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

            // ================ HEADER ==================
            HeaderAuth(
                title = "Edit Profile",
                subtitle = "Auralis"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ================ FORM ====================
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // FULL NAME
                AuralisTextField(
                    value = username,
                    onValueChange = viewModel::onUsernameChange,
                    placeholder = "Username",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // EMAIL
                AuralisTextField(
                    value = email,
                    onValueChange = viewModel::onEmailChange,
                    placeholder = "Email",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // BOTÓN SAVE CHANGES
                AuthActionButton(
                    title = "Save changes",
                    onClick = {
                        if (username.isNotBlank() && email.isNotBlank()) {
                            viewModel.updateProfile()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // TEXTO PARA VOLVER SIN GUARDAR (opcional)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cancel and go back",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = colorScheme.primary,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
    if (isLoading){
        AuralisLoadingOverlay()
    }
}

// PREVIEWS
@Preview(showBackground = true)
@Composable
fun EditUserScreenPreviewLight() {
    CameraTestTheme(darkTheme = false) {
        EditUserScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditUserScreenPreviewDark() {
    CameraTestTheme(darkTheme = true) {
        EditUserScreen(
            innerPadding = PaddingValues(0.dp),
            navController = rememberNavController()
        )
    }
}