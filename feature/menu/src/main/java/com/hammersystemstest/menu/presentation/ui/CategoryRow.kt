package com.hammersystemstest.menu.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hammersystemstest.core.ui.shimmerEffect
import com.hammersystemstest.menu.presentation.model.CategoriesState
import com.hammersystemstest.menu.presentation.model.CategoryItem

@Composable
internal fun CategoryRow(
    state: CategoriesState,
    onItemClick: (category: CategoryItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (state.isLoading)
        ShimmerCategoryRow(modifier = modifier,)
    else
        ActualCategoryRow(state = state, onItemClick = onItemClick, modifier = modifier)
}

@Composable
internal fun ShimmerCategoryRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        repeat(5) {
            ShimmerCategoryItem()
        }
    }
}

@Composable
internal fun ActualCategoryRow(
    state: CategoriesState,
    onItemClick: (category: CategoryItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = state.items, key = { item -> item.name }) {
            CategoryItem(
                model = it,
                onClick = { onItemClick(it) },
                isActive = it.name == state.activeName
            )
        }
    }
}

@Composable
internal fun ShimmerCategoryItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(88.dp, 32.dp)
            .clip(MaterialTheme.shapes.small)
            .shimmerEffect()
    )
}

@Composable
internal fun CategoryItem(
    model: CategoryItem,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = MaterialTheme.shapes.small
    Box(
        modifier = modifier
            .shadow(
                if (isActive) 0.dp else 16.dp,
                ambientColor = Color.Transparent,
                spotColor = DefaultShadowColor.copy(alpha = 0.5f)
            )
            .size(88.dp, 32.dp)
            .clip(shape)
            .background(
                if (isActive) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = model.name,
            style = MaterialTheme.typography.titleSmall,
            color = if (isActive) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}