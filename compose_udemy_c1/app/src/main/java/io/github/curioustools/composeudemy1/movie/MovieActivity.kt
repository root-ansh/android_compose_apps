package io.github.curioustools.composeudemy1.movie

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.curioustools.composeudemy1.base.BaseComposeActivity

class MovieActivity:BaseComposeActivity() {
    @Composable
    override fun Ui(savedInstanceState: Bundle?) {

    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun UiPreview() {
        Ui(null)
    }

}