package com.example.splash

import android.window.SplashScreen
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.necdetzr.datastore.model.DataStoreManager
import com.necdetzr.designsystem.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashPage(
    dataStoreManager: DataStoreManager,
    onNavigateToMain: () -> Unit,
    onNavigateToAuth: () -> Unit
) {
    val isRememberedState = dataStoreManager.rememberMe.collectAsState(initial = null)

    var logoScale by remember { mutableFloatStateOf(0f) }
    var logoAlpha by remember { mutableFloatStateOf(0f) }
    var textAlpha by remember { mutableFloatStateOf(0f) }
    var progressAlpha by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        launch {
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(800, easing = FastOutSlowInEasing)
            ) { value, _ ->
                logoScale = value
                logoAlpha = value
            }
        }

        launch {
            delay(400)
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(600, easing = FastOutSlowInEasing)
            ) { value, _ ->
                textAlpha = value
            }
        }

        launch {
            delay(800)
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            ) { value, _ ->
                progressAlpha = value
            }
        }
    }

    LaunchedEffect(isRememberedState.value) {
        delay(2500)
        when (isRememberedState.value) {
            true -> onNavigateToMain()
            false -> onNavigateToAuth()
            null -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF667eea),
                        Color(0xFF764ba2)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .size(120.dp)
                    .scale(logoScale)
                    .alpha(logoAlpha),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.9f)
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                       painter = painterResource(com.necdetzr.splash.R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier.clip(CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "CryptoTricker",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.alpha(textAlpha)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.welcome_loodos_h1),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
                modifier = Modifier.alpha(textAlpha)
            )

            Spacer(modifier = Modifier.height(64.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(progressAlpha)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = MaterialTheme.colorScheme.background,
                    strokeWidth = 3.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Yükleniyor...",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = "v1.0.0",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.6f),
                modifier = Modifier.alpha(textAlpha)
            )
        }
    }
}