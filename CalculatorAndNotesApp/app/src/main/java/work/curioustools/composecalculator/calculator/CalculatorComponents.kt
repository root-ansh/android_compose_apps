package work.curioustools.composecalculator.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, widthDp = 60, heightDp = 60)
@Composable
fun CalcButton(text:String="1", color: Color = Color.Blue, modifier: Modifier = Modifier, onClick:(String)->Unit={}){
    Box(contentAlignment = Alignment.Center, modifier = Modifier.clip(RoundedCornerShape(48.dp)).background(color).clickable { onClick(text) }.then(modifier)) {
        Text(text = text, fontSize = 24.sp, color = Color.White)
    }
}