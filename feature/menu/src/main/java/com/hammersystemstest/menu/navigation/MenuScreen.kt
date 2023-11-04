package com.hammersystemstest.menu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hammersystemstest.menu.presentation.ui.MainScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MenuRoute.Main.route) {
        composable(route = MenuRoute.Main.route) {
            MainScreen(
                viewModel = koinViewModel(),
            )
        }
    }
}