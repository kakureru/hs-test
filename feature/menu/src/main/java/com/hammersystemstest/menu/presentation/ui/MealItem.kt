package com.hammersystemstest.menu.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hammersystemstest.core.ui.shimmerEffect
import com.hammersystemstest.menu.R
import com.hammersystemstest.menu.presentation.model.MealItem
import com.hammersystemstest.menu.presentation.model.MenuState

internal fun LazyListScope.mealList(
    state: MenuState,
) {
    if (state.isLoading) {
        items(items = listOf(0, 0, 0, 0, 0)) {
            Divider(modifier = Modifier.alpha(0.2f))
            ShimmerMealItem()
        }
    } else {
        items(items = state.items, key = { item -> item.id }) {
            Divider(modifier = Modifier.alpha(0.2f))
            MealItem(
                model = it,
            )
        }
    }
}

@Composable
internal fun ShimmerMealItem(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.height(167.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .clip(MaterialTheme.shapes.large)
                    .shimmerEffect(),
            )
            Column(
                modifier = Modifier
                    .padding(start = 22.dp)
                    .fillMaxHeight()
                    .weight(3f),
            ) {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .height(14.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .shimmerEffect(),
                )
                Spacer(modifier = Modifier.weight(1f))
                ShimmerPriceLabel(
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
internal fun MealItem(
    model: MealItem,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.height(167.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = model.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .clip(MaterialTheme.shapes.large),
            )
            Column(
                modifier = Modifier
                    .padding(start = 22.dp)
                    .fillMaxHeight()
                    .weight(3f),
            ) {
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = model.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(top = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.weight(1f))
                PriceLabel(
                    value = model.price,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
internal fun ShimmerPriceLabel(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(100.dp, 40.dp)
            .clip(MaterialTheme.shapes.medium)
            .shimmerEffect()
    )
}

@Composable
internal fun PriceLabel(
    value: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(
            text = stringResource(id = R.string.price, value),
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}