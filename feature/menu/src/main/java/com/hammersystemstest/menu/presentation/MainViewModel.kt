package com.hammersystemstest.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hammersystemstest.core.runCatchingNonCancellation
import com.hammersystemstest.menu.R
import com.hammersystemstest.menu.domain.Category
import com.hammersystemstest.menu.domain.MenuRepository
import com.hammersystemstest.menu.presentation.model.BannersState
import com.hammersystemstest.menu.presentation.model.CategoryItem
import com.hammersystemstest.menu.presentation.model.MainScreenEffect
import com.hammersystemstest.menu.presentation.model.MainScreenState
import com.hammersystemstest.menu.presentation.model.toDomain
import com.hammersystemstest.menu.presentation.model.toUI
import com.hammersystemstest.menu.presentation.model.toUi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val menuRepository: MenuRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<MainScreenEffect>()
    val uiEffect: SharedFlow<MainScreenEffect> = _uiEffect.asSharedFlow()

    init {
        loadBanners()
        loadCategories()
    }

    private fun loadBanners() = viewModelScope.launch {
        runCatchingNonCancellation {
            _uiState.update {
                it.copy(bannersState = it.bannersState.copy(isLoading = true))
            }
            val urls = menuRepository.getBannerUrls()
            _uiState.update {
                it.copy(bannersState = it.bannersState.copy(urls = urls, isLoading = false))
            }
        }.onFailure {
            _uiState.update { it.copy(bannersState = BannersState(isVisible = false)) }
        }
    }

    private fun loadCategories() = viewModelScope.launch {
        runCatchingNonCancellation {
            _uiState.update {
                it.copy(categoriesState = it.categoriesState.copy(isLoading = true))
            }
            var isFirst = true
            menuRepository.getCategories().collect { categories ->
                val sorted = categories.sortedBy { it.name.lowercase() }
                if (isFirst) {
                    sorted.firstOrNull()?.let {
                        loadMealsForCategory(it)
                        isFirst = false
                    }
                }
                _uiState.update {
                    it.copy(
                        categoriesState = it.categoriesState.copy(
                            isLoading = false,
                            items = sorted.map { it.toUi() },
                            activeName = sorted.firstOrNull()?.name ?: ""
                        )
                    )
                }
            }

        }.onFailure {
            _uiEffect.emit(MainScreenEffect.Error(R.string.error))
        }
    }

    private fun loadMealsForCategory(category: Category) = viewModelScope.launch {
        runCatchingNonCancellation {
            _uiState.update {
                it.copy(menuState = it.menuState.copy(isLoading = true))
            }
            menuRepository.getMealsByCategory(category).collect { meals ->
                _uiState.update {
                    it.copy(
                        menuState = it.menuState.copy(
                            isLoading = false,
                            items = meals.sortedBy { it.name }.map { it.toUI() })
                    )
                }
            }
        }.onFailure {
            _uiEffect.emit(MainScreenEffect.Error(R.string.error))
        }
    }

    fun onCategoryClick(category: CategoryItem) {
        _uiState.update {
            it.copy(categoriesState = it.categoriesState.copy(activeName = category.name))
        }
        loadMealsForCategory(category.toDomain())
    }
}