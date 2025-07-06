package com.necdetzr.loodoscrypto.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF80CBC4),            // Ana renk, butonlar ve aktif elemanlar
    onPrimary = Color.Black,                // Primary rengin üstündeki yazı rengi
    primaryContainer = Color(0xFF004D40),   // Kart veya buton arka planı (primary varyantı)
    onPrimaryContainer = Color.White,       // Primary container üzerindeki yazı rengi

    secondary = Color(0xFFB39DDB),          // Yardımcı (accent) renk
    onSecondary = Color.Black,              // Secondary üstü yazı rengi
    secondaryContainer = Color(0xFF4A148C), // Secondary varyantı, kart vb.
    onSecondaryContainer = Color.White,     // Secondary container üstü yazı rengi

    background = Color(0xFF121212),         // Genel ekran arka planı
    onBackground = Color.White,             // Background üzerindeki metin

    surface = Color(0xFF1E1E1E),            // Kart, dialog gibi yüzeyler
    onSurface = Color.White,                // Surface üstündeki yazılar

    surfaceVariant = Color(0xFF2C2C2C),     // Daha alt seviye yüzey (örn. input arka planı)
    onSurfaceVariant = Color.LightGray,     // Surface variant üstü yazı

    outline = Color(0xFF888888),            // Kenarlıklar, divider'lar

    error = Color(0xFFCF6679),              // Hatalı durum rengi (form hatası vb.)
    onError = Color.Black,                  // Hata renginin üstü yazı rengi
    errorContainer = Color(0xFFB00020),     // Hata kutusu arka planı
    onErrorContainer = Color.White          // Hata kutusu yazı rengi
)


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00796B),            // Ana renk, butonlar ve aktif elemanlar
    onPrimary = Color.White,                // Primary rengin üstündeki yazı rengi
    primaryContainer = Color(0xFFB2DFDB),   // Kart veya buton arka planı (primary varyantı)
    onPrimaryContainer = Color.Black,       // Primary container üzerindeki yazı rengi

    secondary = Color(0xFF9575CD),          // Yardımcı (accent) renk
    onSecondary = Color.White,              // Secondary üstü yazı rengi
    secondaryContainer = Color.White, // Secondary varyantı, kart vb.
    onSecondaryContainer = Color.Black,     // Secondary container üstü yazı rengi

    background = Color.White,         // Genel ekran arka planı
    onBackground = Color.Black,             // Background üzerindeki metin

    surface = Color(0xFFFFFFFF),            // Kart, dialog gibi yüzeyler
    onSurface = Color.Black,                // Surface üstündeki yazılar

    surfaceVariant = Color(0xFFF0F0F0),     // Daha alt seviye yüzey (örn. input arka planı)
    onSurfaceVariant = Color.DarkGray,      // Surface variant üstü yazı

    outline = Color(0xFF444444),            // Kenarlıklar, divider'lar

    error = Color(0xFFB00020),              // Hatalı durum rengi (form hatası vb.)
    onError = Color.White,                  // Hata renginin üstü yazı rengi
    errorContainer = Color(0xFFFFDAD4),     // Hata kutusu arka planı
    onErrorContainer = Color(0xFF370617)    // Hata kutusu yazı rengi
)


@Composable
fun LoodosCryptoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}