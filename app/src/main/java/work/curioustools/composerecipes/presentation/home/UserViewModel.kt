package work.curioustools.composerecipes.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import work.curioustools.composerecipes.network.models.UserResponse
import work.curioustools.composerecipes.network.usecases.CreateUserUseCase
import work.curioustools.composerecipes.network.usecases.GetAllUsersUseCase
import work.curioustools.composerecipes.network.usecases.GetSingleUserUseCase
import work.curioustools.composerecipes.network.usecases.UpdateUserUseCase
import work.curioustools.composerecipes.network.utils.ApiResponse
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject  constructor(
     private val getAllUsersUseCase: GetAllUsersUseCase,
     private val getSingleUserUseCase: GetSingleUserUseCase,
     private val createUserUseCase: CreateUserUseCase,
     private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel() {

     val allUsersLiveData:LiveData<ApiResponse<List<UserResponse>>>  = getAllUsersUseCase.responseLiveData


     fun getAllUsers(){
          getAllUsersUseCase.requestForData(Unit)
     }

}