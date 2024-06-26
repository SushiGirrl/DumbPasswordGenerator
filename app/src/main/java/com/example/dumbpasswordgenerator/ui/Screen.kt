package com.example.dumbpasswordgenerator.ui

sealed class Screen(val route: String) {
    object StartScreen: Screen(route = "start_screen")
    object PasswordScreen: Screen(route = "password_screen")
}
