package com.example.qrcodebank

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qrcodebank.ui.camera.CameraScreen
import com.example.qrcodebank.ui.main.MainScreen

class Navigation {
    @Composable
    fun getNavController(mainScreen: MainScreen): NavHostController {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Route.Library.route) {

            composable(Route.Library.route) { mainScreen.LibraryScreen() }
            composable(Route.Camera.route) { CameraScreen().CameraView() }
        }
        return navController
    }
}