package com.hammersystemstest.app.ui

import com.hammersystemstest.app.R

sealed class BottomBarRoute(
    val route: String,
    val titleResId: Int,
    val iconResId: Int,
) {
    object Menu : BottomBarRoute(
        route = "menu",
        titleResId = R.string.menu,
        iconResId = R.drawable.ic_menu
    )

    object Profile : BottomBarRoute(
        route = "profile",
        titleResId = R.string.profile,
        iconResId = R.drawable.ic_profile
    )

    object Cart : BottomBarRoute(
        route = "cart",
        titleResId = R.string.cart,
        iconResId = R.drawable.ic_cart
    )
}