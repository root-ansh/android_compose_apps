package io.github.curioustools.composeudemy1.base

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

object MyComposeColors {
    val White = Color.White
    val Black = Color.Black
    val Grey = Color.LightGray
    val DarkGrey = Color.Gray

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

fun String.toColor():Color{
    return runCatching { Color(this.toColorInt()) }.getOrElse { Color.Black }
}
fun Color.asHexString(): String {
    val alpha = this.alpha*255
    val red = this.red * 255
    val green = this.green * 255
    val blue = this.blue * 255
    return String.format("#%02x%02x%02x",red.toInt(), green.toInt(), blue.toInt())
}