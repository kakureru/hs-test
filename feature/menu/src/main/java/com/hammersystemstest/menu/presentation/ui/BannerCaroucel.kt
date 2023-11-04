package com.hammersystemstest.menu.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import com.hammersystemstest.core.ui.shimmerEffect
import com.hammersystemstest.menu.presentation.model.BannersState

@OptIn(ExperimentalFoundationApi::class)
internal fun LazyListScope.bannerCarousel(
    state: BannersState,
    modifier: Modifier = Modifier,
) {
    if (state.isVisible) {
        item {
            Column(modifier = modifier.animateContentSize()) {
                val listState = rememberLazyListState()
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
                    state = listState,
                ) {
                    items(items = state.urls, key = { it }) {
                        Banner(url = it)
                    }
                }
            }
        }
    }
}

@Composable
internal fun Banner(
    url: String,
    modifier: Modifier = Modifier,
) {
    val width = (LocalConfiguration.current.screenWidthDp - 48).dp
    val maxWidth = 400.dp

    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .width(min(width, maxWidth))
            .clip(MaterialTheme.shapes.medium)
            .height(112.dp),
        contentScale = ContentScale.Crop,
    )
}
