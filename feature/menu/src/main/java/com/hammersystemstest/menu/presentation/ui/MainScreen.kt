package com.hammersystemstest.menu.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import com.hammersystemstest.core.ui.theme.HammersystemstestTheme
import com.hammersystemstest.menu.R
import com.hammersystemstest.menu.presentation.MainViewModel
import com.hammersystemstest.menu.presentation.model.BannersState
import com.hammersystemstest.menu.presentation.model.CategoriesState
import com.hammersystemstest.menu.presentation.model.CategoryItem
import com.hammersystemstest.menu.presentation.model.MainScreenEffect
import com.hammersystemstest.menu.presentation.model.MainScreenState
import com.hammersystemstest.menu.presentation.model.MealItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.uiState.collectAsState()
    MainScreenUi(
        state = state,
        effect = viewModel.uiEffect,
        modifier = modifier,
        onCategoryClick = { viewModel.onCategoryClick(it) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainScreenUi(
    state: MainScreenState,
    onCategoryClick: (category: CategoryItem) -> Unit,
    effect: Flow<MainScreenEffect>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        effect.collect { effect ->
            when (effect) {
                is MainScreenEffect.Error -> {
                    Toast.makeText(context, context.getString(effect.msgResId), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MenuTopBar(
                onQrClick = { Unit }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            bannerCarousel(
                state = state.bannersState,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            )
            stickyHeader {
                CategoryRow(
                    state = state.categoriesState,
                    onItemClick = onCategoryClick,
                )
            }
            mealList(state = state.menuState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuTopBar(
    onQrClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = { CityDropDown() },
        actions = {
            IconButton(onClick = onQrClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_qr),
                    contentDescription = "qr"
                )
            }
        }
    )
}

@Composable
private fun CityDropDown(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Москва",
            style = MaterialTheme.typography.titleMedium,
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "expand")
        }
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    HammersystemstestTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainScreenUi(
                state = MainScreenState(
                    categoriesState = CategoriesState(
                        items = listOf(
                            CategoryItem(0, "Beef"),
                            CategoryItem(1, "Pork"),
                            CategoryItem(2, "Sugar")
                        ),
                        activeName = "Beef"
                    )
                ),
                effect = emptyFlow(),
                onCategoryClick = {},
            )
        }
    }
}