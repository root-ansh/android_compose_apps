package io.github.curioustools.composeudemy1.full_notes.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.curioustools.composeudemy1.base.MyComposeColors
import io.github.curioustools.composeudemy1.base.MyComposeColors.Grey
import io.github.curioustools.composeudemy1.base.MyComposeColors.White
import io.github.curioustools.composeudemy1.base.asHexString
import io.github.curioustools.composeudemy1.base.toColor
import io.github.curioustools.composeudemy1.base.toIcon
import io.github.curioustools.composeudemy1.full_notes.enums.Routes
import io.github.curioustools.composeudemy1.full_notes.model.NoteModel

@Preview
@Composable
fun NoteHorizontal(
    pos: Int = -1,
    note: NoteModel = NoteModel.mock(),
    onCheckChanged: (Int) -> Unit = {},
    onDelete : (Int) -> Unit = {},
    onClick : (NoteModel) -> Unit = {}
) {
    val isChecked = remember { mutableStateOf( note.isChecked) }
    val isWhite = note.color == White.asHexString()

    Surface(
        shape = RoundedCornerShape(4.dp),
        color = note.color.toColor(),
        modifier = Modifier
            .padding(8.dp)
            .defaultMinSize(minHeight = 100.dp)
            .border(1.dp, Grey)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(note) }
            .padding(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                modifier = Modifier
                    .size(20.dp)
                    .padding(top = 12.dp),
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = !isChecked.value
                    onCheckChanged(pos)
                }
            )
            Column(Modifier.weight(1f)) {
                Text(
                    text = note.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (isChecked.value) TextDecoration.LineThrough else null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = note.details,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textDecoration = if (isChecked.value) TextDecoration.LineThrough else null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Icons.Default.Delete.toIcon(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(36.dp)
                    .border(1.dp, Grey, CircleShape)
                    .clip(CircleShape)
                    .clickable { onDelete(pos) }
                    .background(White)
                    .padding(4.dp)
            )()
        }


    }

}




@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)@Composable
fun HomeScreen(controller: NavHostController? = null, arguments: Bundle? = null) {
    val notes = remember {
        (
            mutableStateListOf(*NoteModel.mockList().toTypedArray())
        )
    }
    Scaffold(containerColor = MyComposeColors.Purple40) { p ->
        Box(
            modifier = Modifier
                .padding(p)
                .fillMaxSize()
                .background(White)


        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {

                itemsIndexed(notes.toList()) { i, note ->
                    NoteHorizontal(
                        pos = i,
                        note = note,
                        onCheckChanged = { notes[it].isChecked =!notes[it].isChecked },
                        onDelete = {notes.removeAt(it)},
                        onClick = {controller?.navigate(Routes.Details.filledRoute(it))}
                    )
                }
            }

            Button(
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(vertical = 42.dp, horizontal = 18.dp)
                ,
                onClick = { /*TODO*/ }
            ) {
                Icons.Filled.Add.toIcon()()
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "New Note")
            }



        }
    }

}