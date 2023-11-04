package work.curioustools.composecalculator.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import work.curioustools.composecalculator.calculator.CalculatorController.CalculatorAction
import work.curioustools.composecalculator.calculator.CalculatorController.CalculatorAction.*

@Preview(showBackground = true)
@Composable
fun CalculatorScreen(terminalValue:String="0", onButtonPress:(CalculatorAction)->Unit = {}) {
    val orange = Color(0xFFFB8C00)
    val blue = Color(0xFF1E88E5)
    val green = Color(0xFF43A047)
    val white = Color(0xffffffff)

    Box(modifier = Modifier.fillMaxSize().background(white).padding(8.dp)) {
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = terminalValue,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 64.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            val btnModifier = {weight:Float -> Modifier.aspectRatio(weight).weight(weight)}


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CalcButton("AC", green,btnModifier(2f)){onButtonPress(Clear)}
                CalcButton("⌫", green,btnModifier(1f)){onButtonPress(Delete)}
                CalcButton("÷", orange,btnModifier(1f)){onButtonPress(Symbol('÷'))}

            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                CalcButton("7", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("8", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("9", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("×", orange,btnModifier(1f)){onButtonPress(Symbol('×'))}
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                CalcButton("4", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("5", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("6", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("-", orange,btnModifier(1f)){onButtonPress(Symbol('-'))}
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                CalcButton("1", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("2", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("3", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("+", orange,btnModifier(1f)){onButtonPress(Symbol('+'))}
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CalcButton("0", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("00", blue,btnModifier(1f)){onButtonPress(Number(it))}
                CalcButton("•", blue,btnModifier(1f)){onButtonPress(Symbol('•'))}
                CalcButton("=", green,btnModifier(1f)){onButtonPress(Equals)}

            }
        }

    }
    //AndroidView(modifier = Modifier.background(Color.Red), factory = { it })

}


