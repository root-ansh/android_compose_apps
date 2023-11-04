package work.curioustools.composecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import work.curioustools.composecalculator.calculator.CalculatorController
import work.curioustools.composecalculator.todo.TodoListController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { chooserScreen() }
    }

    @Preview(showBackground = true)
    @Composable
    fun chooserScreen() {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Column {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = { CalculatorController.update(this@MainActivity) }
                ) {
                    Text("Calculator")
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = { TodoListController.update(this@MainActivity) }
                ) {
                    Text("Todos")
                }


            }

        }

    }
}