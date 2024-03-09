package io.github.curioustools.composeudemy1.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.curioustools.composeudemy1.utils.ComposeUtils.Colors.Black
import io.github.curioustools.composeudemy1.utils.ComposeUtils.Colors.Grey
import io.github.curioustools.composeudemy1.utils.ComposeUtils.Colors.White

object ComposeUtils{
    object Colors {
        val White = Color.White
        val Black = Color.Black
        val Grey = Color.LightGray

        val Purple80 = Color(0xFFD0BCFF)
        val PurpleGrey80 = Color(0xFFCCC2DC)
        val Pink80 = Color(0xFFEFB8C8)
        val Purple40 = Color(0xFF6650a4)
        val PurpleGrey40 = Color(0xFF625b71)
        val Pink40 = Color(0xFF7D5260)

        val Green80 = Color(0xFFA5D6A7)
        val GreenGrey80 = Color(0xFFC5E1C5)
        val Blue80 = Color(0xFFB3E5FC)
        val Green40 = Color(0xFF4CAF50)
        val GreenGrey40 = Color(0xFF66BB6A)
        val Blue40 = Color(0xFF2196F3)

        val Red80 = Color(0xFFEF9A9A)
        val RedGrey80 = Color(0xFFE57373)
        val Orange80 = Color(0xFFFFCC80)
        val Red40 = Color(0xFFF44336)
        val RedGrey40 = Color(0xFFD32F2F)
        val Orange40 = Color(0xFFFF9800)

        val Yellow80 = Color(0xFFFFF59D)
        val YellowGrey80 = Color(0xFFFAF0AA)
        val Brown80 = Color(0xFFBCAAA4)
        val Yellow40 = Color(0xFFFFEB3B)
        val YellowGrey40 = Color(0xFFFFF176)
        val Brown40 = Color(0xFFA1887F)
    }
    
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


@SuppressLint("ModifierFactoryUnreferencedReceiver")
inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


fun ImageVector.toIcon(modifier: Modifier = Modifier, desc: String = "icon"): ComposableBlock {
    return { Icon(imageVector = this, contentDescription = desc, modifier = modifier) }

}

typealias ComposableBlock = @Composable () -> Unit