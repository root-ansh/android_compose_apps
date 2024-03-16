package io.github.curioustools.composeudemy1.tipcalculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HorizontalRule
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.curioustools.composeudemy1.base.BaseComposeActivity
import io.github.curioustools.composeudemy1.base.ComposeUtils
import io.github.curioustools.composeudemy1.base.MyComposeColors
import io.github.curioustools.composeudemy1.base.MyComposeColors.Black
import io.github.curioustools.composeudemy1.base.MyComposeColors.Blue40
import io.github.curioustools.composeudemy1.base.MyComposeColors.Red40
import io.github.curioustools.composeudemy1.base.MyComposeColors.White
import io.github.curioustools.composeudemy1.base.toIcon
import java.util.Locale
import kotlin.math.roundToInt


class TipCalculatorActivity : BaseComposeActivity() {

    @Preview(showBackground = true)
    @Composable
    fun Screen(share: Double = 0.0) {
        Surface(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .defaultMinSize(minHeight = 150.dp)
                .clip(RoundedCornerShape(12.dp)),
            color = MyComposeColors.GreenGrey80
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    style = ComposeUtils.Typography.bodyMedium,
                    fontWeight = FontWeight.Normal,
                    text = "Share of 1 person is:",
                )
                Text(
                    text = "$ ${String.format(Locale.getDefault(),"%.2f",share)}/-",
                    style = ComposeUtils.Typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                )

            }

        }
    }

    @Composable
    @Preview
    private fun StylishText(){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Blue40,
                        shadow = Shadow(Blue40, Offset(8f,4f), blurRadius = 8f)
                    )
                ){append("hello ")}
                withStyle(
                    style = SpanStyle(
                        color = Red40,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold

                    )
                ){append("welcome ")}

                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Cursive,
                        background = MyComposeColors.Yellow40
                    )
                ){append("to the Tip Calculator. ")}


                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 4.sp,
                        textDecoration = TextDecoration.combine(listOf(TextDecoration.LineThrough,TextDecoration.Underline))
                    )
                ){append("More info:")}

                // clickable?
                // web/phone links?
                //html reader spannable??




            }
        )

    }


    @Preview(showBackground = true)
    @Composable
    private fun InputBlock(
        error: String = "",
        onValChange: (String) -> Unit = {},

        ) {
        val totalBillState = remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            value = totalBillState.value,
            label = { Text(text = "Bill Amount") },
            placeholder = { Text(text = "Bill Amount") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Rounded.AttachMoney.toIcon(),
            trailingIcon = null,
            visualTransformation = VisualTransformation.None,
            onValueChange = {
                totalBillState.value = it
                onValChange.invoke(it.trim())
            },
            singleLine = true,
            isError = error.isNotBlank(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onValChange.invoke(totalBillState.value.trim())
                    keyboardController?.hide()

                }
            ),
            colors = OutlinedTextFieldDefaults.colors(),
            supportingText = {
                if (error.isNotBlank()) {
                    Text(text = error, color = ComposeUtils.LightColorScheme.error)
                }
            }

        )

    }

    @Preview(showBackground = true)
    @Composable
    private fun CounterBlock(count: Int = 1, onAdd: () -> Unit = {}, onMinus: () -> Unit = {}) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Number of shares", modifier = Modifier.weight(1f))
            FilledIconButton(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Black,
                    contentColor = White
                ),
                onClick = onAdd,
                content = Icons.Filled.Add.toIcon(modifier = Modifier.padding(4.dp))
            )
            Text(text = count.toString(), modifier = Modifier.padding(horizontal = 12.dp))
            FilledIconButton(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Black,
                    contentColor = White
                ),
                onClick = onMinus,
                content = Icons.Filled.HorizontalRule.toIcon(modifier = Modifier.padding(4.dp)),

                )

        }

    }

    @Preview(showBackground = true)
    @Composable
    private fun TipAmountBlock(count: Double = 0.0,title:String ="Tip Amount") {
        OutlinedTextField(
            value = "${String.format(Locale.getDefault(),"%.2f",count)}/-",
            label = { Text(text = title) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Rounded.AttachMoney.toIcon(),
            trailingIcon = null,
            visualTransformation = VisualTransformation.None,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = Color.Transparent,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
        )

    }

    @Preview(showBackground = true)
    @Composable
    private fun SliderBlock(onSliderChange: (Int) -> Unit = {}) {
        val currentSliderPos = remember { mutableIntStateOf(0) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(color = Black.copy(0.2f))
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = "${currentSliderPos.intValue} %")
            Slider(
                value = currentSliderPos.intValue.toFloat(),
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = Black,
                    activeTrackColor = Black,
                    inactiveTrackColor = Black.copy(0.2f)
                ),
                steps = 21,
                onValueChange = {
                    currentSliderPos.intValue = it.roundToInt()
                    onSliderChange.invoke(it.roundToInt())
                }
            )

        }

    }


    @Preview
    @Composable
    fun CalculatorUI(screenValue: (Double) -> Unit = {}) {
        val error = remember { mutableStateOf("") }
        val shares = remember { mutableIntStateOf(1) }
        val tipPercent = remember { mutableIntStateOf(0) }
        val tipAmount = remember { mutableDoubleStateOf(0.0) }
        val userAmount = remember { mutableDoubleStateOf(0.0) }
        val totalAmount = remember { mutableDoubleStateOf(0.0) }
        val calculate = {
            tipAmount.doubleValue = (userAmount.doubleValue * tipPercent.intValue / 100)
            totalAmount.doubleValue = (userAmount.doubleValue + tipAmount.doubleValue)
            val userValue = (totalAmount.doubleValue / shares.intValue)
            screenValue.invoke(userValue)
        }

        Card(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                InputBlock(error.value) {
                    val inputAsDouble = it.toDoubleOrNull() ?: 0.0
                    if (inputAsDouble > 1000) error.value = "please enter a smaller amount"
                    else error.value = ""
                    userAmount.doubleValue = inputAsDouble
                    calculate()
                }
                TipAmountBlock(tipAmount.doubleValue)
                TipAmountBlock(totalAmount.doubleValue, "Total Amount")
                CounterBlock(
                    shares.intValue,
                    onAdd = {
                        shares.intValue += 1
                        calculate()

                    },
                    onMinus = {
                        shares.intValue = maxOf(1, shares.intValue - 1)
                        calculate()
                    }
                )
                SliderBlock {
                    tipPercent.intValue = it
                    calculate()
                }
            }
        }
    }


    @Composable
    override fun Ui(savedInstanceState: Bundle?) {
        val amount = remember { mutableDoubleStateOf(0.0) }
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                StylishText()

                Screen(amount.doubleValue)
                Spacer(modifier = Modifier.padding(4.dp))
                CalculatorUI {
                    amount.doubleValue = it
                }
            }
        }

    }


    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
        showSystemUi = true
    )
    @Composable
    fun Preview() {
        Ui(null)
    }
}

