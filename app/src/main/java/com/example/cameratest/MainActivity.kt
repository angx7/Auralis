package com.example.cameratest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cameratest.navigation.Routes
import com.example.cameratest.ui.screens.CameraScreen
import com.example.cameratest.ui.screens.HomeScreen
import com.example.cameratest.ui.theme.CameraTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraTestTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Home.route) {
                    composable(Routes.Home.route) { HomeScreen(navController) }
                    composable(Routes.Camera.route) { CameraScreen() }
                }
            }
        }
    }
}
