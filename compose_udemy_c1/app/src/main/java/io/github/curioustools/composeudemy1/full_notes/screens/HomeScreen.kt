package io.github.curioustools.composeudemy1.full_notes.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import io.github.curioustools.composeudemy1.base.MyComposeColors.White

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)@Composable
fun HomeScreen(controller: NavHostController? = null) {
    Scaffold(containerColor = White) { p ->
        Box(
            modifier = Modifier
                .padding(p)
                .fillMaxSize()
        ) {

        }
    }

}