package io.github.curioustools.composeudemy1.proj3_compose_in_frag.fragments

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.curioustools.composeudemy1.utils.BaseComposeFragment
import io.github.curioustools.composeudemy1.utils.ComposeUtils

@OptIn(ExperimentalMaterial3Api::class)
class CounterFragment : BaseComposeFragment() {

    @Preview
    @Composable
    fun MyCard(
        text: String = "Rs. 0/-",
        shape: Shape = RoundedCornerShape(8.dp),
        color: Color = ComposeUtils.Colors.White,
        textColor: Color = ComposeUtils.Colors.Black,
        onClick: () -> Unit = {},
    ){
        var starter = Modifier.padding(8.dp).wrapContentHeight().defaultMinSize(120.dp,120.dp)
        starter =
            if (shape.hashCode() == CircleShape.hashCode()) starter.wrapContentWidth()
            else starter.fillMaxWidth()
        Card(
            modifier = Modifier.padding(8.dp),
            shape = shape,
            colors = CardDefaults.elevatedCardColors(containerColor = color),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
            onClick = { onClick.invoke() }
        ) {
            Column(modifier = starter, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(
                    text = text,
                    style = ComposeUtils.Typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }
        }
    }


    @Preview
    @Composable
    fun MyCardOld(
        text: String = "Rs. 0/-",
        shape: Shape = RoundedCornerShape(8.dp),
        color: Color = ComposeUtils.Colors.White,
        textColor: Color = ComposeUtils.Colors.Black,
        onClick: () -> Unit = {},
    ) {

        var starter = Modifier.padding(8.dp)
        starter =
            if (shape.hashCode() == CircleShape.hashCode()) starter.wrapContentWidth()
            else starter.fillMaxWidth()
        starter = starter
            .wrapContentHeight()
            .defaultMinSize(minHeight = 120.dp, minWidth = 120.dp)
//            .clip(shape)
            .clickable { onClick.invoke() }


        Surface(
            modifier = starter,
            color = color,
            shape = shape,
            shadowElevation = 8.dp,
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    style = ComposeUtils.Typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }
        }


    }

    @Composable
    override fun Ui(bundle: Bundle?) {
        val amount = remember { mutableIntStateOf(0) }
        val onClick = {
            amount.intValue += 5
        }

        Surface(color = ComposeUtils.Colors.White) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyCard(text = "Rs ${amount.intValue}/-")
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                MyCard(
                    text = "+5",
                    shape = CircleShape,
                    color = ComposeUtils.Colors.Blue40,
                    textColor = ComposeUtils.Colors.White,
                    onClick = onClick
                )


            }
        }

    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun UIPreview() {
        this.Ui(null)
    }


}