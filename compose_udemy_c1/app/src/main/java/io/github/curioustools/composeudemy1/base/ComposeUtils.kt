package io.github.curioustools.composeudemy1.base

import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object ComposeUtils{

    val DarkColorScheme = darkColorScheme(
//        primary = Black,
//        onPrimary = White,
//        secondary = Grey,
//        onSecondary = White,
//        tertiary = Grey,
//        onTertiary = White,
//        background = Black,
//        onBackground = White,
//        surface = Black,
//        onSurface = White,
    )

    val LightColorScheme = lightColorScheme(
//        primary = White,
//        onPrimary = Black,
//        secondary = Grey,
//        onSecondary = Black,
//        tertiary = Grey,
//        onTertiary = Black,
//        background = White,
//        onBackground = Black,
//        surface = White,
//        onSurface = Black,

    )
    val Typography = Typography(
        bodyLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.5.sp),
        titleLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 0.sp),
        labelSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp)
    )


}

