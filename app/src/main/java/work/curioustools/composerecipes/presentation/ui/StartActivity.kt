package work.curioustools.composerecipes.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import work.curioustools.composerecipes.presentation.commons.AppTheme
import work.curioustools.composerecipes.presentation.commons.BaseActivity

@AndroidEntryPoint
class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppNavigation(Screens.UserListScreen)
            }
        }
    }

}


