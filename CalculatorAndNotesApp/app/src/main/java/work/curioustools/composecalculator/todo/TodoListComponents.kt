package work.curioustools.composecalculator.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import work.curioustools.composecalculator.todo.TodoListController.TodoInteraction
import work.curioustools.composecalculator.todo.TodoListController.Todo
import work.curioustools.composecalculator.todo.TodoListController.TodoInteraction.CHECK_CLICK
import work.curioustools.composecalculator.todo.TodoListController.TodoInteraction.DELETE_CLICK
import work.curioustools.composecalculator.todo.TodoListController.TodoInteraction.ITEM_CLICK


@Preview(showBackground = true)
@Composable
fun TodoItem(data: Todo = MockData.mock2(), pos: Int = -1,parent:String = "", onClick: (TodoInteraction, Todo,String, Int) -> Unit = { _, _, _,_ -> }) {
    val alpha =if(data.isChecked) 0.5f else 1f
    val style = if(data.isChecked) TextStyle(color = Color.Black,textDecoration = TextDecoration.LineThrough) else TextStyle.Default
    Column(Modifier.fillMaxWidth().padding(8.dp,4.dp)) {
        Row(Modifier.fillMaxWidth().border(1.dp, Color.Gray,RoundedCornerShape(24.dp)).clickable { onClick(ITEM_CLICK, data,parent, pos) }, verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = data.isChecked, onCheckedChange = {onClick(CHECK_CLICK,data,parent,pos)})
            Text(text = data.msg, modifier = Modifier.weight(1f).alpha(alpha), style = style )
            IconButton(onClick = {onClick(DELETE_CLICK,data,parent,pos)}) { Icon(imageVector = Icons.Outlined.Close, contentDescription = "Delete") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoHeader(data:String="Hello"){
    Text(text = data, modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), fontSize = 28.sp, fontWeight = FontWeight.Light )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ChooserChip(text:String = "Office", initial:Boolean = false, onClick:(Boolean)->Unit = {}){
    FilterChip(
        onClick = {onClick(initial) },
        label = { Text(text) },
        selected = initial,
        leadingIcon = if (initial) { { Icon(imageVector = Icons.Filled.Done, contentDescription = "Done icon", modifier = Modifier.size(
            FilterChipDefaults.IconSize)) } } else { null },
    )
}

