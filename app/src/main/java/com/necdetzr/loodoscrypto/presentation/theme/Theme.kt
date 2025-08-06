package com.necdetzr.loodoscrypto.presentation.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF818CF8),            // Açık indigo (dark mode'a uygun)
    onPrimary = Color.Black,

    primaryContainer = Color(0xFF312E81),
    onPrimaryContainer = Color(0xFFEEF2FF),

    secondary = Color(0xFFFF8A8A),
    onSecondary = Color.Black,

    secondaryContainer = Color(0xFF7F1D1D),
    onSecondaryContainer = Color(0xFFFFEAEA),

    background = Color(0xFF0F172A),
    onBackground = Color(0xFFE0E7FF),

    surface = Color(0xFF1E293B),
    onSurface = Color(0xFFE5E7EB),

    surfaceVariant = Color(0xFF374151),
    onSurfaceVariant = Color(0xFF9CA3AF),

    outline = Color(0xFF6B7280),
    outlineVariant = Color(0xFF94A3B8),

    error = Color(0xFFF87171),
    onError = Color.Black,

    errorContainer = Color(0xFF7F1D1D),
    onErrorContainer = Color(0xFFFFE2E2)
)





val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4F46E5),            // Indigo (Ana renk)
    onPrimary = Color.White,                // Kontrast için beyaz

    primaryContainer = Color(0xFFE0E7FF),   // Açık indigo arka plan
    onPrimaryContainer = Color(0xFF1E1B4B), // Koyu yazı

    secondary = Color(0xFFFF6B6B),          // Coral (Vurgu)
    onSecondary = Color.White,

    secondaryContainer = Color(0xFFFFE5E5),
    onSecondaryContainer = Color(0xFF7A1B1B),

    background = Color(0xFFF9FAFB),         // Çok açık gri
    onBackground = Color(0xFF111827),       // Koyu lacivertimsi

    surface = Color(0xFFFFFFFF),            // Saf beyaz
    onSurface = Color(0xFF1F2937),          // Gri-mavi yazı

    surfaceVariant = Color(0xFFE5E7EB),
    onSurfaceVariant = Color(0xFF4B5563),

    outline = Color(0xFF9CA3AF),
    outlineVariant = Color(0xFFD1D5DB),

    error = Color(0xFFDC2626),
    onError = Color.White,

    errorContainer = Color(0xFFFEE2E2),
    onErrorContainer = Color(0xFF7F1D1D)
)





@Composable
fun LoodosCryptoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+

    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {


    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)

            // Status bar'ın rengini TopBar'ınızın rengiyle aynı yapın
            val statusBarColor = colorScheme.primary
            window.statusBarColor = statusBarColor.toArgb()

            // Status bar'daki ikonların (saat, pil simgesi) rengini belirleyin.
            // Arka plan rengi açık rense ikonlar koyu olmalı.
            insetsController.isAppearanceLightStatusBars = statusBarColor.luminance() > 0.5
        }
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}