package io.github.curioustools.composeudemy1.base

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@SuppressLint("ComposableNaming")
@Composable
fun toastComp(s:String){
    Toast.makeText(LocalContext.current,s,Toast.LENGTH_SHORT).show()
}

fun Long.toCustomDateString(): String {
    val currentTime = System.currentTimeMillis()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    val currentCalendar = Calendar.getInstance()

    val format = SimpleDateFormat("dd MMM, hh:mm aa", Locale.getDefault())

    return when {
        calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) - 1 -> {
            "Last Year"
        }
        calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) + 1 -> {
            "Upcoming"
        }
        Math.abs(this - currentTime) <= 3600000 -> {
            "Just Now"
        }
        calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH) &&
                calendar.get(Calendar.DAY_OF_MONTH) == currentCalendar.get(Calendar.DAY_OF_MONTH) -> {
            "Today, " + SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(calendar.time)
        }

        else -> {
            format.format(calendar.time)
        }
    }
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