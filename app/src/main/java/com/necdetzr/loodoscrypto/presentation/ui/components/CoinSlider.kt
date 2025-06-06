package com.necdetzr.loodoscrypto.presentation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CoinSlider(images: List<Int>) {
    val currentIndex = remember { mutableIntStateOf(0) }
    val offsetX = remember { Animatable(0f) }


    LaunchedEffect(currentIndex.intValue) {
        delay(4000)

        offsetX.animateTo(
            targetValue = -1000f,
            animationSpec = tween(durationMillis = 1000)
        )

        val nextIndex = (currentIndex.intValue + 1) % images.size
        currentIndex.intValue = nextIndex

        offsetX.snapTo(0f)

        offsetX.animateTo(
            targetValue = 1000f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp))
            .graphicsLayer {
                translationX = offsetX.value
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = images[currentIndex.intValue]),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(50.dp)
        )
    }
}
