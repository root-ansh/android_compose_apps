package io.github.curioustools.composeudemy1.movie

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import io.github.curioustools.composeudemy1.R
import io.github.curioustools.composeudemy1.base.BaseComposeActivity
import io.github.curioustools.composeudemy1.base.ComposeUtils
import io.github.curioustools.composeudemy1.base.MyComposeColors
import io.github.curioustools.composeudemy1.base.MyComposeColors.Black
import io.github.curioustools.composeudemy1.base.toIcon
import org.json.JSONArray
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
class MovieActivity : BaseComposeActivity() {

    @Preview
    @Composable
    fun MovieDetails(controller: NavHostController?=null, arguments: Bundle?=null) {
        val id = arguments?.getString("id").orEmpty().toIntOrNull()
        val name = arguments?.getString("name").orEmpty()
        if (id == null) {
            Toast.makeText(LocalContext.current, "id can't be empty", Toast.LENGTH_SHORT).show()
            controller?.popBackStack()
            return
        }
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                MyToolbar("Item#$id", icon = Icons.AutoMirrored.Filled.ArrowBack.toIcon(modifier = Modifier.clickable { controller?.popBackStack() }))
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                MovieBlock(
                    id = id,
                    name = name,
                    img = "https://picsum.photos/id/$id/200/300",
                )

            }

        }

    }

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
            val onClick: (Int) -> Unit = {  }
            items(data.count()){id ->
                MovieBlock(
                    id = id,
                    name = "Movie#$id",
                    img = "https://picsum.photos/id/$id/200/300",
                ){
                    controller?.navigate(Screens.DETAILS.baseRoute+"/$id?name=boy")
                }
            }
            
        }
    }



    @Preview
    @Composable
    fun MyToolbar(title:String = "Movies!", icon:@Composable ()-> Unit = {}){
        Surface(modifier = Modifier.shadow(4.dp)) {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = icon,
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
                NavHost(navController = controller, startDestination = Screens.HOME.baseRoute){
                    composable(Screens.HOME.baseRoute) { MovieList(controller) }

                    composable(
                        route = "${Screens.DETAILS.baseRoute}/{id}?name={name}",
                        arguments = listOf(
                            navArgument("id"){type = NavType.StringType},
                            navArgument("name"){type = NavType.StringType},
                            )
                    ) {


                        MovieDetails(controller,it.arguments)
                    }

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
        fun data(context:Context): JSONArray {
            val jsonStr :String? = try {
                val inputStream: InputStream = context.resources.openRawResource(R.raw.data)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charsets.UTF_8)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
            return JSONArray(jsonStr?:"[]")
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