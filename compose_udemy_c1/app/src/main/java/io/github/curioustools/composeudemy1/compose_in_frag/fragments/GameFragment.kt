package io.github.curioustools.composeudemy1.compose_in_frag.fragments

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.curioustools.composeudemy1.base.BaseComposeFragment
import io.github.curioustools.composeudemy1.base.ComposeUtils
import io.github.curioustools.composeudemy1.base.MyComposeColors
import kotlin.concurrent.thread
import kotlin.random.Random

class GameFragment : BaseComposeFragment() {


    @Preview(showBackground = true)
    @Composable
    fun Display(
        lastChoices: List<Int> = listOf(),
        userScore: Int = 0,
        compInput: String = "?",
        user: String = "?"
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(MyComposeColors.Blue80)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = ComposeUtils.Typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    text = compInput
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    maxLines = 2,
                    text = "Last Choices: ${lastChoices.joinToString(" ")}"
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(MyComposeColors.Green80)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = ComposeUtils.Typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    text = user
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    maxLines = 2,
                    textAlign = TextAlign.End,
                    text = "Score: $userScore/5"
                )
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun Result(won: Boolean = true, counter: Int = 3) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(MyComposeColors.Black)
                .padding(8.dp),
            style = ComposeUtils.Typography.bodyLarge,
            color = MyComposeColors.White,
            text = " You ${if (won) "Won!" else "Lost."} Starting new game in : $counter"
        )

    }

    @Preview(showBackground = true)
    @Composable
    fun Choice(
        id: Int = 1, choices: List<Int> = listOf(1, 2, 3, 4, 5), onClick: (Int) -> Unit = {}
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, MyComposeColors.Blue80)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(modifier = Modifier.padding(vertical = 8.dp), text = "Question $id")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                choices.forEach { choice ->
                    Surface(
                        shape = RoundedCornerShape(24.dp), color = MyComposeColors.Red40
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                                .clickable { onClick.invoke(choice) },
                            text = choice.toString(),
                            style = ComposeUtils.Typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MyComposeColors.White

                        )
                    }
                }

            }

        }
    }


    @Composable
    override fun Ui(bundle: Bundle?) {

        val counter = remember { mutableIntStateOf(5) }
        val button = remember { mutableStateOf("Start") }
        val showChoice = remember { mutableStateOf(true) }
        val showResult = remember { mutableStateOf(false) }
        val userSelectedChoice = remember { mutableStateOf("?") }
        val compChoice = remember { mutableStateOf("?") }
        val score = remember { mutableIntStateOf(0) }
        val compLastSelections = remember { mutableStateOf(mutableListOf<Int>()) }
        val userChoices = remember { mutableStateOf(mutableListOf<Int>()) }
        val won = remember { mutableStateOf(false) }
        val resultCounter = remember { mutableIntStateOf(3) }
        val onChoiceSelected: (Int) -> Unit = {
            userSelectedChoice.value = it.toString()
            val compChoiceInt = userChoices.value.toList().random()
            compChoice.value = compChoiceInt.toString()
            compLastSelections.value.add(compChoiceInt)
            val hasWon = compChoiceInt == it
            if (hasWon) {
                score.intValue++
                won.value = true
            } else {
                won.value = false
            }
            showChoice.value = false
            showResult.value = true
            thread {
                Thread.sleep(600)
                resultCounter.intValue = 2
                Thread.sleep(600)
                resultCounter.intValue = 1
                Thread.sleep(600)
                activity?.runOnUiThread {
                    userChoices.value.clear()
                    userChoices.value = mutableListOf(
                        Random.nextInt(1, 10),
                        Random.nextInt(11, 20),
                        Random.nextInt(21, 30),
                        Random.nextInt(31, 40),
                        Random.nextInt(41, 50)
                    )
                    userSelectedChoice.value = "?"
                    compChoice.value = "?"
                    showChoice.value = true
                    showResult.value = false
                    counter.intValue--
                    button.value = when (counter.intValue) {
                        5 -> "Start"
                        0 -> "Restart"
                        else -> "Stop game"

                    }
                }
            }
        }
        val onStartStopBtnClick: () -> Unit = {
            when (counter.intValue) {
                5, 0 -> {
                    userChoices.value.clear()
                    userChoices.value = mutableListOf(
                        Random.nextInt(1, 10),
                        Random.nextInt(11, 20),
                        Random.nextInt(21, 30),
                        Random.nextInt(31, 40),
                        Random.nextInt(41, 50)
                    )
                    userSelectedChoice.value = "?"
                    compChoice.value = "?"
                    score.intValue = 0
                    showChoice.value = true
                    showResult.value = false
                    counter.intValue--
                    button.value = when (counter.intValue) {
                        5 -> "Start"
                        0 -> "Restart"
                        else -> "Stop game"
                    }
                    if (counter.intValue <= 0) {
                        counter.intValue = 5
                        compLastSelections.value = mutableListOf()
                    }
                }

                else -> {
                    userChoices.value.clear()
                    showChoice.value = false
                    showResult.value = false
                    counter.intValue = 0
                }

            }
        }

        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Display(
                    lastChoices = compLastSelections.value,
                    userScore = score.intValue,
                    compInput = compChoice.value,
                    user = userSelectedChoice.value
                )
                AnimatedVisibility(visible = showChoice.value) {
                    Choice(
                        id = 5 - counter.intValue + 1,
                        choices = userChoices.value,
                        onClick = onChoiceSelected
                    )
                }
                AnimatedVisibility(visible = showResult.value) {
                    Result(won = won.value, counter = resultCounter.intValue)
                }
                Button(onClick = onStartStopBtnClick) { Text(text = button.value) }


            }
        }

    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun Preview() {
        this.Ui(null)
    }

}