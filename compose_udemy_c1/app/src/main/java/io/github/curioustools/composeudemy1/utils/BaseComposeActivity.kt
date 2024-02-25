package io.github.curioustools.composeudemy1.utils

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

abstract class BaseComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme: Boolean = isSystemInDarkTheme()
            val colorScheme = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                    val context = LocalContext.current
                    if (darkTheme) ComposeUtils.LightColorScheme //dynamicDarkColorScheme(context)
                    else ComposeUtils.LightColorScheme// dynamicLightColorScheme(context)
                }

                darkTheme -> ComposeUtils.LightColorScheme//ComposeUtils.DarkColorScheme
                else -> ComposeUtils.LightColorScheme
            }
            MaterialTheme(colorScheme = colorScheme, typography = ComposeUtils.Typography) {
                Ui(savedInstanceState)
            }

        }
    }

    @Composable
    abstract fun Ui(savedInstanceState: Bundle?)
}