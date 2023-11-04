package com.hammersystemstest.menu.navigation

sealed class MenuRoute(val route: String) {
    object Main : MenuRoute(route = "main")
}