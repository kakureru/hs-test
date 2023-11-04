package com.hammersystemstest.menu.presentation.model

internal data class MainScreenState(
    val bannersState: BannersState = BannersState(),
    val categoriesState: CategoriesState = CategoriesState(),
    val menuState: MenuState = MenuState()
)

internal data class BannersState(
    val urls: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val isVisible: Boolean = true,
)

internal data class CategoriesState(
    val items: List<CategoryItem> = emptyList(),
    val activeName: String = "",
    val isLoading: Boolean = true,
)

internal data class MenuState(
    val items: List<MealItem> = emptyList(),
    val isLoading: Boolean = true,
)

sealed class MainScreenEffect {
    class Error(val msgResId: Int) : MainScreenEffect()
}