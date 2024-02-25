package io.github.curioustools.composeudemy1.proj3_compose_in_frag.fragments

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.curioustools.composeudemy1.utils.BaseComposeFragment


class NotificationsFragment: BaseComposeFragment(){
    //val notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
    @Composable
    override fun Ui(bundle: Bundle?) {
        Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            OutlinedButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Facebook,contentDescription = null)
                Text(text = "Notifications")
            }
            OutlinedButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Facebook,contentDescription = null)
                Text(text = "Notifications")
            }
        }


    }

}
