package work.curioustools.composerecipes.presentation.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import work.curioustools.composerecipes.network.models.UserResponse
import work.curioustools.composerecipes.network.utils.ApiResponse
import work.curioustools.composerecipes.presentation.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(
    id: String?,
    viewModel: UserViewModel = hiltViewModel()
) {

    val user by remember { viewModel.singleUserState }
    when(user){
        is ApiResponse.Success -> {
            val cUser = (user as? ApiResponse.Success<UserResponse>)?.data
            if(cUser == null){
                Text(text = "Something Went wrong. details: $user")
                return
            }
            else{
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    elevation = CardDefaults.cardElevation(8.dp),

                    ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape),
                            painter = rememberAsyncImagePainter(cUser.avatar),
                            contentDescription = null,

                            )
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "${cUser.firstName} ${cUser.lastName}", fontSize = 24.sp)
                            Text(text = cUser.email, fontSize = 16.sp)


                        }
                    }
                }
            }

        }
        is ApiResponse.Loading -> CircularProgressIndicator()
        else -> {
            Text(text = "Something Went wrong. details: $user")
        }
    }


    viewModel.getUser(id?.toIntOrNull()?:-1)

}