package work.curioustools.composerecipes.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import work.curioustools.composerecipes.commons.AppTheme
import work.curioustools.composerecipes.commons.BaseActivity
import work.curioustools.composerecipes.network.utils.ApiResponse
import work.curioustools.composerecipes.presentation.home.DashboardSharedViewModel
import work.curioustools.composerecipes.presentation.home.UserViewModel

@AndroidEntryPoint
class StartActivity : BaseActivity() {

    private val userSharedViewModel by viewModels<DashboardSharedViewModel>()
    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
        userViewModel.allUsersLiveData.observe(this){
            if(it==null){
                log("response is null")
            }
            else{
                when(it){
                    is ApiResponse.Failure -> {
                        log("FAILURE|${it.errorCodeType()}|${it.errorMsg}|${it.errorCode}")
                    }
                    is ApiResponse.Success -> {
                        log("SUCCESS|${it.page}|${it.perPage}|${it.total}|${it.totalPages}||${it.data}|")

                    }
                }
            }
        }
        userViewModel.getAllUsers()
    }

    private fun log(s:String){
        Toast.makeText(this,"received data", Toast.LENGTH_SHORT).show()
        Timber.tag("DashboardActivity").e(s)
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


