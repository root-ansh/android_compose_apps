package io.github.curioustools.composeudemy1.full_notes

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.curioustools.composeudemy1.base.BaseComposeActivity
import io.github.curioustools.composeudemy1.full_notes.enums.Routes
import io.github.curioustools.composeudemy1.full_notes.enums.Routes.Details
import io.github.curioustools.composeudemy1.full_notes.enums.Routes.Home
import io.github.curioustools.composeudemy1.full_notes.screens.DetailsScreen
import io.github.curioustools.composeudemy1.full_notes.screens.HomeScreen

class NotesActivity : BaseComposeActivity(){
    @Composable
    override fun Ui(savedInstanceState: Bundle?) {
        val controller = rememberNavController()
        NavHost(navController = controller, startDestination = Details.routeID ){
            composable(Home.routeID){ HomeScreen(controller)}
            composable(Details.routeID){ DetailsScreen(controller,Details.getNote(it.arguments))}
        }
    }


    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
        showSystemUi = true
    )
    @Composable
    fun Preview() {
        Ui(null)
    }
}