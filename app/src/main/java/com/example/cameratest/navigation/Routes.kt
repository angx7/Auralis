package com.example.cameratest.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Camera : Routes("camera")
}
