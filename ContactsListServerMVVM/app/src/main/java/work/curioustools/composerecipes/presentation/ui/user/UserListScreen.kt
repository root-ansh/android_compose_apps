package work.curioustools.composerecipes.presentation.ui.user

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import work.curioustools.composerecipes.network.models.UserResponse
import work.curioustools.composerecipes.network.utils.ApiResponse
import work.curioustools.composerecipes.presentation.ui.Screens
import work.curioustools.composerecipes.presentation.viewmodel.UserViewModel

@Composable
fun UserListScreen(
    viewModel: UserViewModel = hiltViewModel(),
    router: NavHostController
) {
    val users by remember { viewModel.allUsersState }

    when(users){
        is ApiResponse.Success -> {
            val castedUsers = (users as? ApiResponse.Success<List<UserResponse>>)?.data?: listOf()
            LazyColumn {
                items(castedUsers) { user ->
                    SingleUserItem(
                        user = user,
                        onUserClick = { str -> router.navigate("${Screens.UserDetail.route}/$str") }
                    )
                }
            }
        }
        is ApiResponse.Loading -> CircularProgressIndicator()

        else -> {
            Text(text = "Something Went wrong. details: $users")
        }

    }


    viewModel.getAllUsers()



}