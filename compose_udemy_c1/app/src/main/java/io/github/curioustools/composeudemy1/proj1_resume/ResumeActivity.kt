package io.github.curioustools.composeudemy1.proj1_resume

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.OpenInBrowser
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import io.github.curioustools.composeudemy1.R
import io.github.curioustools.composeudemy1.utils.BaseComposeActivity
import io.github.curioustools.composeudemy1.utils.ComposeUtils


class ResumeActivity : BaseComposeActivity() {

    @Preview(showBackground = true)
    @Composable
    fun ProfileIcon(modifier: Modifier = Modifier) {
        Surface(
            modifier = modifier
                .size(150.dp)
                .padding(5.dp),
            shape = CircleShape,
            border = BorderStroke(0.5.dp, ComposeUtils.Colors.Blue40),
            shadowElevation = 4.dp
        ) {

            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "profile image",
                modifier = modifier.size(135.dp),
                contentScale = ContentScale.Crop
            )

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun SingleProject(title: String = "App", link: String = "link", showDivider: Boolean = true) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                        this@ResumeActivity.startActivity(intent)
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileIcon(modifier = Modifier.size(56.dp))
                Text(
                    text = title,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            if (showDivider) {
                Divider(modifier = Modifier.padding(8.dp))
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Projects(modifier: Modifier = Modifier) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .defaultMinSize(minHeight = 24.dp),
            color = ComposeUtils.Colors.White,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, ComposeUtils.Colors.Grey),
        ) {

            val data = listOf("App1", "App2", "App3", "App5")
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(data) { i, it ->
                    SingleProject(it, showDivider = i != data.lastIndex)
                }
            }
        }

    }

    @Composable
    fun SocialMedias(modifier: Modifier = Modifier) {
        val icon: @Composable (String, ImageVector) -> Unit = { link, icon ->
            Icon(
                imageVector = icon,
                contentDescription = link.substringAfterLast("/"),
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                        this@ResumeActivity.startActivity(intent)
                    }
            )
        }
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            icon("https://www.facebook.com/anshsachdeva", Icons.Rounded.OpenInBrowser)
            icon("https://www.facebook.com/anshsachdeva", Icons.Rounded.OpenInBrowser)
            icon("https://www.facebook.com/anshsachdeva", Icons.Rounded.OpenInBrowser)
        }
    }


    @Composable
    fun Info() {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                style = ComposeUtils.Typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                text = "Hello Folks,I am",
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Ansh Sachdeva",
                style = ComposeUtils.Typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun CardHeader() {


        val showProjects = remember { mutableStateOf(false) }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .defaultMinSize(minHeight = 140.dp),
            color = ComposeUtils.Colors.White,
            shape = CutCornerShape(12.dp),
            shadowElevation = 8.dp,

            border = BorderStroke(8.dp, ComposeUtils.Colors.Yellow80),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                ProfileIcon(
                    Modifier
                        .padding(top = 16.dp)
                        .size(100.dp)
                )
                Info()
                SocialMedias(modifier = Modifier.padding(4.dp))
                Divider(modifier = Modifier.padding(4.dp))
                OutlinedButton(
                    modifier = Modifier.padding(12.dp),
                    onClick = { showProjects.value = !showProjects.value }) {
                    Text(text = "View Projecs", color = ComposeUtils.Colors.Black)
                }
                AnimatedVisibility(visible = showProjects.value){
                    Projects(Modifier.padding(16.dp))
                }


            }

        }

    }

    @Composable
    override fun Ui(savedInstanceState: Bundle?) {
        Scaffold {systemPaddings ->
            Surface(modifier = Modifier.padding(systemPaddings), color = ComposeUtils.Colors.Blue40) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(12.dp)
                ) {
                    CardHeader()
                }
            }
        }
    }



    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun UiPreview() {
        Ui(null)
    }


}

