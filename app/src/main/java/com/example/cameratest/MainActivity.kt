package com.example.cameratest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.navigation.EditUserScreenRoute
import com.example.cameratest.navigation.LoginScreenRoute
import com.example.cameratest.navigation.RegisterScreenRoute
import com.example.cameratest.ui.screens.auth.EditUserScreen
import com.example.cameratest.ui.screens.auth.LoginScreen
import com.example.cameratest.ui.screens.auth.RegisterScreen
import com.example.cameratest.ui.theme.CameraTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CameraTestTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = LoginScreenRoute
                    ){
                        composable<LoginScreenRoute> {
                            LoginScreen(
                                innerPadding = innerPadding,
                                navController = navController
                            )
                        }
                        composable<RegisterScreenRoute> {
                            RegisterScreen(
                                innerPadding = innerPadding,
                                navController = navController
                            )
                        }
                        composable<EditUserScreenRoute> {
                            EditUserScreen(
                                innerPadding = innerPadding,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
