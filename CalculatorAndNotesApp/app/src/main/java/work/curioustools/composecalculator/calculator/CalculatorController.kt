package work.curioustools.composecalculator.calculator

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.murzagalin.evaluator.Evaluator

object CalculatorController {
    private val _output = MutableLiveData<String>()
    private val output: LiveData<String> = _output

    private fun onButtonClick(pressed: CalculatorAction) {
        var last = output.value.orEmpty()
        when (pressed) {
            is CalculatorAction.Symbol -> {
                var symbol = pressed.symbol
                if (symbol == '•') symbol = '.'
                if (last.isNotBlank() && last.last() != symbol) _output.postValue("$last$symbol")
            }

            is CalculatorAction.Number -> {
                val symbol = pressed.number
                _output.postValue("$last$symbol")
            }

            CalculatorAction.Delete -> {
                if (last.isNotEmpty()) _output.postValue(last.substring(0, last.lastIndex))
            }

            CalculatorAction.Clear -> {
                _output.postValue("")
            }

            CalculatorAction.Equals -> {
                last = last.replace("×", "*")
                last = last.replace("÷", "/")
                last = last.replace("÷", "/")
                val result = kotlin.runCatching { Evaluator().evaluateDouble(last) }.getOrNull()
                _output.postValue(result?.toString() ?: "")
            }

        }
    }

    sealed class CalculatorAction {
        object Clear : CalculatorAction()
        object Delete : CalculatorAction()
        object Equals : CalculatorAction()
        data class Number(val number: String) : CalculatorAction()
        data class Symbol(val symbol: Char) : CalculatorAction()

    }

    fun update(activity: ComponentActivity) {
        output.observe(activity) {
            activity.setContent { CalculatorScreen(it ?: "", ::onButtonClick) }
        }
        _output.postValue("")
    }
}