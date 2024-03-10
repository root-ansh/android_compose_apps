package io.github.curioustools.composeudemy1.movie

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import io.github.curioustools.composeudemy1.R
import io.github.curioustools.composeudemy1.base.BaseComposeActivity
import io.github.curioustools.composeudemy1.base.ComposeUtils
import io.github.curioustools.composeudemy1.base.MyComposeColors
import io.github.curioustools.composeudemy1.base.MyComposeColors.Black
import org.json.JSONArray

@OptIn(ExperimentalMaterial3Api::class)
class MovieActivity : BaseComposeActivity() {

    @Preview
    @Composable
    fun MovieBlock(
        id:Int = 0,
        name: String = "User Name",
        img: String ="",
        onClick: (Int) -> Unit = {}
    ) {
        Surface(
            color = if(id%2==0) MyComposeColors.Brown80 else MyComposeColors.Green80,
            modifier = Modifier.size(150.dp, 180.dp)) {
            Box(modifier = Modifier.clickable { onClick(id) }) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(img)
                        .setHeader("User-Agent", "Mozilla/5.0")
                        .crossfade(true)
                        .placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_error)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = "icon",
                    contentScale = ContentScale.FillBounds,
                    onError = { it.result.throwable.printStackTrace() }

                )
                Text(
                    text = name.capitalize(Locale.current),
                    textAlign = TextAlign.Center,
                    style = ComposeUtils.Typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(MyComposeColors.Grey.copy(0.3f))
                        .padding(vertical = 8.dp)

                )
            }
        }
    }

    @Preview
    @Composable
    fun MovieList(controller: NavHostController?= null) {
        val data = data2()
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier.height(450.dp)
        ) {
            val onClick: (Int) -> Unit = {  controller?.navigate(Screens.DETAILS.route) }
            items(data.count()){
                MovieBlock(
                    id = it,
                    name = "Movie#$it",
                    img = "https://picsum.photos/id/$it/200/300",
                    onClick = onClick,
                )
            }
            
        }
    }



    @Preview
    @Composable
    fun MyToolbar(){
        Surface(modifier = Modifier.shadow(4.dp)) {
            TopAppBar(
                title = { Text(text = "Movies!") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyComposeColors.Blue80,
                    titleContentColor = Black,
                    actionIconContentColor = Black
                )
            )
        }
    }

    @Composable
    override fun Ui(savedInstanceState: Bundle?) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {MyToolbar()}) { padding ->
            Column(Modifier.padding(padding)) {
                val controller: NavHostController = rememberNavController()
                NavHost(navController = controller, startDestination = Screens.HOME.route){
                    composable(Screens.HOME.route){MovieList(controller)}
                    composable(Screens.DETAILS.route){MovieBlock()}

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

    companion object {
        fun data(): JSONArray {
            return JSONArray(
                """
                    [{"createdAt":"2024-03-09T01:46:42.446Z","name":"Howard Nader DVM","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/789.jpg","id":"1"},{"createdAt":"2024-03-09T10:35:50.185Z","name":"Misty Terry","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/50.jpg","id":"2"},{"createdAt":"2024-03-08T21:01:55.644Z","name":"Darrel West","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1021.jpg","id":"3"},{"createdAt":"2024-03-09T00:48:04.551Z","name":"Mrs. Sheri Luettgen","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1113.jpg","id":"4"},{"createdAt":"2024-03-09T12:45:40.865Z","name":"Martha Mosciski DDS","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1050.jpg","id":"5"},{"createdAt":"2024-03-09T06:18:15.030Z","name":"Guadalupe Fahey","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/755.jpg","id":"6"},{"createdAt":"2024-03-09T17:19:07.885Z","name":"Candace Wilkinson","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/991.jpg","id":"7"},{"createdAt":"2024-03-09T15:07:37.022Z","name":"Charlene Lynch","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/689.jpg","id":"8"},{"createdAt":"2024-03-08T19:46:17.597Z","name":"Jose Mosciski","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/151.jpg","id":"9"},{"createdAt":"2024-03-09T01:54:25.139Z","name":"Pauline Pacocha","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/458.jpg","id":"10"},{"createdAt":"2024-03-09T11:09:21.449Z","name":"Alfredo Johnston","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/713.jpg","id":"11"}]
                """.trimIndent()
            )
        }
        private val lightColorHexCodes = listOf(
            "#F0F8FF", // AliceBlue
            "#FAEBD7", // AntiqueWhite
            "#7FFFD4", // Aquamarine
            "#F5F5DC", // Beige
            "#FFE4C4", // Bisque
            "#8A2BE2", // BlueViolet
            "#DEB887", // BurlyWood
            "#5F9EA0", // CadetBlue
            "#7FFF00", // Chartreuse
            "#6495ED", // CornflowerBlue
            "#FFF8DC", // Cornsilk
            "#00FFFF", // Cyan (Aqua)
            "#8B008B", // DarkMagenta
            "#00FF00", // Lime
            "#F0FFFF", // Azure
            "#FF7F50", // Coral
            "#00CED1", // DarkTurquoise
            "#FFD700", // Gold
            "#20B2AA", // LightSeaGreen
            "#90EE90"  // LightGreen
        )

        fun data2() = 1..1000

    }

}