package work.curioustools.composecalculator.todo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import work.curioustools.composecalculator.todo.TodoListController.TodoInteraction
import work.curioustools.composecalculator.todo.TodoListController.TodoSection
import work.curioustools.composecalculator.todo.TodoListController.Todo


@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NewEntryPopup(onDismissRequest: () -> Unit = {}, onConfirmation: (String?,String?) -> Unit ={_,_ -> }, ) {
    var text by rememberSaveable { mutableStateOf("") }
    var officeSelected by remember { mutableStateOf(false) }
    var homeSelected by remember { mutableStateOf(false) }
    var growthSelected by remember { mutableStateOf(false) }
    var otherSelected by remember { mutableStateOf(false) }
    Dialog(onDismissRequest =onDismissRequest,){
        val setAllFalse = {
            officeSelected = false
            homeSelected = false
            growthSelected = false
            otherSelected = false
        }
        Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White).border(1.dp,Color.Gray, RoundedCornerShape(16.dp)).padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Your Message") },
                placeholder = { Text("Your Message") },
                singleLine = false
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)){
                ChooserChip("Work", officeSelected) {
                    setAllFalse()
                    officeSelected = !officeSelected
                }
                ChooserChip("Home", homeSelected) {
                    setAllFalse()
                    homeSelected = !homeSelected
                }

                ChooserChip("Growth", growthSelected) {
                    setAllFalse()
                    growthSelected = !growthSelected
                }

                ChooserChip("Other", otherSelected) {
                    setAllFalse()
                    otherSelected = !otherSelected
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                TextButton(onClick = onDismissRequest) { Text(text = "Cancel")}
                TextButton(
                    onClick = {
                        val parent = when {
                            officeSelected -> "Work"
                            homeSelected -> "Home"
                            growthSelected -> "Growth"
                            otherSelected -> "Other"
                            else -> null
                        }
                        onConfirmation(text, parent)

                    },
                    modifier = Modifier.padding(horizontal = 4.dp)) { Text(text = "Done")}
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun TodoListScreen(sections:List<TodoSection> = MockData.mockSectionList(), onItemClick: (TodoInteraction, Todo, String, Int) -> Unit = { _, _, _, _ -> }, onNewItem:(String, String)->Unit = { _, _ ->}){
    var showDialog by  remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        val ctx = LocalContext.current
        if(showDialog){
            NewEntryPopup({showDialog=false}){msg:String?,cat:String? ->
                if(msg.isNullOrBlank()) Toast.makeText(ctx,"msg must not be blank",Toast.LENGTH_SHORT).show()
                else if(cat.isNullOrBlank()) Toast.makeText(ctx,"category must be selected",Toast.LENGTH_SHORT).show()
                else {
                    onNewItem(msg,cat)
                    showDialog = false
                }
            }

        }
        LazyColumn(modifier = Modifier.padding(16.dp)){
            sections.map { section->
                item { TodoHeader(section.title) }
                itemsIndexed(section.entries){pos,entry -> TodoItem(entry,pos,section.title,onItemClick)}
            }
        }
        FloatingActionButton(onClick = {showDialog=!showDialog}, modifier = Modifier
            .padding(8.dp)
            .align(Alignment.BottomEnd)) {
            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                Text(text = "Add new")
            }
        }
    }
}


