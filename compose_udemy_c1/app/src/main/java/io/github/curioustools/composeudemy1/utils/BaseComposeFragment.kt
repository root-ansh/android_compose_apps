package io.github.curioustools.composeudemy1.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

abstract class BaseComposeFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).also {
            it.setViewCompositionStrategy(getViewCompositionStrategy())
            it.setContent{
                Surface(modifier = Modifier.fillMaxSize()) {
                    Ui(savedInstanceState)
                }
            }
        }
    }

    open fun getViewCompositionStrategy(): ViewCompositionStrategy {
        return ViewCompositionStrategy.DisposeOnDetachedFromWindow
    }

    @Composable
    abstract fun Ui(bundle: Bundle?)
}