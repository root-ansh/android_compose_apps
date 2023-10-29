package work.curioustools.composerecipes.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import work.curioustools.composerecipes.commons.AppTheme
import work.curioustools.composerecipes.commons.BaseActivity
import work.curioustools.composerecipes.presentation.home.UserSharedViewModel
import work.curioustools.composerecipes.presentation.home.UserViewModel

@AndroidEntryPoint
class StartActivity : BaseActivity() {

    private val userSharedViewModel by viewModels<UserSharedViewModel>()
    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }


    @Composable
    private fun Content() {
        AppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Text("Android")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Content()
    }
}


