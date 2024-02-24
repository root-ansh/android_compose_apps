package io.github.curioustools.composeudemy1.proj2_tipcalculator

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.curioustools.composeudemy1.utils.BaseComposeActivity
import io.github.curioustools.composeudemy1.utils.ComposeUtils


class TipCalculatorActivity : BaseComposeActivity() {

    @Composable
    override fun Ui() {
        Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Top ,
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                PerPersonShare()
                Spacer(modifier = Modifier.padding(4.dp))
                CalculatorUI()
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun PerPersonShare(share: Double = 0.0) {
        Surface(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .defaultMinSize(minHeight = 150.dp)
                .clip(RoundedCornerShape(12.dp)),
            color = ComposeUtils.Colors.GreenGrey80
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
                    text = "$ $share/-",
                    style = ComposeUtils.Typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                )

            }

        }
    }



    @Composable
    fun CalculatorUI(){
        Surface(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(12.dp)),
            color = ComposeUtils.Colors.White
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
               BillInput()
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun BillInput(modifier: Modifier = Modifier,onValChange:(String)->Unit = {}) {


        val totalBillState = remember { mutableStateOf("") }
        val validState = remember(totalBillState.value) { totalBillState.value.trim().isNotEmpty() && (totalBillState.value.toDoubleOrNull() ?: 0.0) < 1000 }

        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Bill Amount") },
            value = totalBillState.value,
            leadingIcon = { Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription ="Symbol" )},
            onValueChange = {totalBillState.value = it},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions{
                if (!validState) return@KeyboardActions
                else{
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            }
        )

    }


    @Preview(showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
        showSystemUi = true
    )
    @Composable
    fun Preview() {
        Ui()
    }
}

