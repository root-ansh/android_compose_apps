package work.curioustools.composecalculator.todo

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object MockData{
    fun mock1()= TodoListController.Todo(LoremIpsum(20).values.first(), true)
    fun mock2()= TodoListController.Todo(LoremIpsum(20).values.first(), false)
    fun mockList() = mutableListOf(
        TodoListController.Todo(LoremIpsum(20).values.first(), true),
        TodoListController.Todo(LoremIpsum(20).values.first(), false),
        TodoListController.Todo(LoremIpsum(20).values.first(), false),
        TodoListController.Todo(LoremIpsum(20).values.first(), false),
        TodoListController.Todo(LoremIpsum(20).values.first(), true),
    )
    fun mockSectionList() = listOf(
        TodoListController.TodoSection("Sec1", mockList().toMutableList()),
        TodoListController.TodoSection("Sec2", mockList().toMutableList()),
        TodoListController.TodoSection("Sec3", mockList().toMutableList()),
    )
}

object TodoListController {
    enum class TodoInteraction { ITEM_CLICK, CHECK_CLICK,DELETE_CLICK }
    data class Todo(val msg: String , var isChecked: Boolean)
    data class TodoSection(val title:String, val entries:MutableList<Todo>)
    private val _output = MutableLiveData<List<TodoSection>>()
    private val output: LiveData<List<TodoSection>> = _output
    fun update(activity: ComponentActivity){
        output.observe(activity){ list->
            activity.setContent {
                TodoListScreen(list,  ::onInteraction, ::onNew)
            }
        }
        _output.postValue(listOf())


    }
    private fun onInteraction(interaction: TodoInteraction, todo: Todo, parentTitle: String, pos: Int) {
        Log.d("TDLC", "onInteraction() called with: interaction = $interaction, todo = $todo, parentTitle = $parentTitle, pos = $pos")
        val list = output.value?.toMutableList()?: mutableListOf()
        val parentIdx = list.indexOfFirst { it.title.equals(parentTitle, ignoreCase = true) }
        if (!list.indices.contains(parentIdx)) return
        val parent = list[parentIdx]
        if (pos !in parent.entries.indices) return
        val item = parent.entries[pos]
        when(interaction){
            TodoInteraction.ITEM_CLICK ,TodoInteraction.CHECK_CLICK -> item.isChecked = item.isChecked.not()
            TodoInteraction.DELETE_CLICK -> {
                parent.entries.removeAt(pos)
                if(parent.entries.isEmpty())  list.removeAt(parentIdx)
            }
        }
        _output.postValue(list)
    }

    private fun onNew(msg:String,title: String){
        val list: MutableList<TodoSection> = output.value?.toMutableList() ?: mutableListOf()
        val parent = list.firstOrNull { it.title.equals(title, ignoreCase = true) }
        val todo = Todo(msg, false)
        if (parent == null) list.add(TodoSection(title, mutableListOf(todo)))
        else parent.entries.add(todo)
        _output.postValue(list)

    }


}