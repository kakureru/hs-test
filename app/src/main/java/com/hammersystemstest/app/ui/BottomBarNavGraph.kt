package com.hammersystemstest.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hammersystemstest.menu.navigation.MenuRoute
import com.hammersystemstest.menu.navigation.MenuScreen

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarRoute.Menu.route
    ) {
        composable(
            route = BottomBarRoute.Menu.route
        ) {
            MenuScreen()
        }
        composable(
            route = BottomBarRoute.Profile.route
        ) {
            Box(modifier = Modifier.fillMaxSize())
        }
        composable(
            route = BottomBarRoute.Cart.route
        ) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}