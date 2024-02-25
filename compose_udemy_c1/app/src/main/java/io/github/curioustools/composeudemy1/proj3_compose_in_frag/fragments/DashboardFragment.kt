package io.github.curioustools.composeudemy1.proj3_compose_in_frag.fragments

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.curioustools.composeudemy1.utils.BaseComposeFragment

class DashboardFragment:BaseComposeFragment(){
    @Composable
    override fun Ui(bundle: Bundle?) {

        Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            Text(text = "Dashboard!")
        }

    }

}