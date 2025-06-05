package com.necdetzr.loodoscrypto.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.necdetzr.loodoscrypto.R

// Set of Material typography styles to start with
val CustomFont = FontFamily(
    Font(R.font.quicksandbold, FontWeight.Bold),
    Font(R.font.quicksandsemibold,FontWeight.SemiBold),
    Font(R.font.quicksandmedium,FontWeight.Medium),
    Font(R.font.quicksandregular,FontWeight.Normal),
    Font(R.font.quicksandlight,FontWeight.Light),

)
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

)