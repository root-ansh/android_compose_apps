package io.github.curioustools.composeudemy1.full_notes.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.curioustools.composeudemy1.base.MyComposeColors.Black
import io.github.curioustools.composeudemy1.base.MyComposeColors.DarkGrey
import io.github.curioustools.composeudemy1.base.MyComposeColors.Green80
import io.github.curioustools.composeudemy1.base.MyComposeColors.Grey
import io.github.curioustools.composeudemy1.base.MyComposeColors.Purple80
import io.github.curioustools.composeudemy1.base.MyComposeColors.White
import io.github.curioustools.composeudemy1.base.MyComposeColors.Yellow80
import io.github.curioustools.composeudemy1.base.toColor
import io.github.curioustools.composeudemy1.base.toCustomDateString
import io.github.curioustools.composeudemy1.base.asHexString
import io.github.curioustools.composeudemy1.base.toIcon
import io.github.curioustools.composeudemy1.base.toastComp
import io.github.curioustools.composeudemy1.full_notes.enums.Routes


private fun getNextColor(colorStr: String): String {
    val currentColor =  colorStr.toColor()
    val next =  when (currentColor) {
        White -> Purple80
        Purple80 -> Green80
        Green80 -> Yellow80
        Yellow80 -> White
        else -> White
    }

    return next.asHexString()
}

@Composable
@Preview
fun Title(
    title: String = "Title",
    color: String = "#ffbbff",
    onBackPress:()-> Unit = {},
    onColorChange: (String) -> Unit = {},
    onValueChanged: (String) -> Unit = {}
) {
    val titleData = remember { mutableStateOf(title) }
    val showHint = remember { mutableStateOf(title.isEmpty()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        IconButton(
            onClick = { onBackPress()},
            modifier = Modifier
                .padding(top = 4.dp)
                .size(24.dp),
            content = Icons.AutoMirrored.Filled.ArrowBackIos.toIcon(modifier = Modifier.fillMaxSize())
        )
        if (showHint.value) {
            Text(
                text = "Title",
                modifier = Modifier
                    .weight(1f)
                    .clickable { showHint.value = false },
                fontSize = 28.sp,
                color = Grey,

                )
        } else {
            BasicTextField(
                modifier = Modifier.weight(1f),
                value = titleData.value,
                onValueChange = {
                    titleData.value = it
                    onValueChanged(it)
                    showHint.value = it.isEmpty()
                },
                textStyle = TextStyle(fontSize = 28.sp)
            )
        }
        IconButton(
            onClick = { onColorChange(color) },
            modifier = Modifier
                .background(color.toColor(), CircleShape)
                .border(1.dp, DarkGrey, CircleShape)
                .size(30.dp),

            content = {}
        )

    }

}

@SuppressLint("ModifierParameter")
@Composable
@Preview
fun Details(
    modifier: Modifier = Modifier,
    text: String = "lorem ipsum",
    createdAt: Long = 1710604688290,
    updatedAt: Long = System.currentTimeMillis(),
    onValueChanged: (String) -> Unit = {}
) {
    val data = remember { mutableStateOf(text) }
    Column(modifier = modifier) {
        BasicTextField(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(horizontal = 24.dp, vertical = 8.dp),
            value = data.value,
            onValueChange = {
                data.value = it
                onValueChanged(it)
            },

            textStyle = TextStyle(fontSize = 16.sp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(Grey)
                .padding(horizontal = 2.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(0.65f),
                textAlign = TextAlign.Center,
                text = "Created: ${createdAt.toCustomDateString()}"
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .width(1.dp)
                    .fillMaxHeight(),
                color = Black
            )
            Text(
                modifier = Modifier.weight(0.35f),
                textAlign = TextAlign.Center,
                text = "Updated: ${updatedAt.toCustomDateString()}"
            )

        }

    }
}
//https://stackoverflow.com/a/67919210/7500651


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)
@Composable
fun DetailsScreen(controller: NavHostController? = null, args:Bundle? = null) {
    val note= Routes.Details.getNote(args)
    if (note == null) {
        toastComp("something went wrong!")
        controller?.popBackStack()
        return
    }
    val title = remember { mutableStateOf(note.title) }
    val details = remember { mutableStateOf(note.details) }
    val updatedAt = remember { mutableStateOf(note.updatedAt) }
    val color = remember { mutableStateOf(note.color) }

    Scaffold(containerColor = color.value.toColor()) { p ->
        Box(
            modifier = Modifier
                .padding(p)
                .fillMaxSize()
        ) {
            Column {
                Title(
                    title = title.value,
                    color = color.value,
                    onBackPress = { controller?.popBackStack() },
                    onColorChange = { color.value = getNextColor(it) },
                    onValueChanged = { title.value = it },
                    )
                Details(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    text = details.value,
                    createdAt = note.createdAt,
                    updatedAt = updatedAt.value,
                    onValueChanged = {
                        details.value = it
                        updatedAt.value = System.currentTimeMillis()
                    }
                )
            }
            Button(
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(vertical = 42.dp, horizontal = 18.dp),
                onClick = { /*TODO*/ }
            ) {
                Icons.Filled.Save.toIcon()()
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "Save Note")
            }


        }
    }

}