package io.github.curioustools.composeudemy1.base

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.vector.ImageVector


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