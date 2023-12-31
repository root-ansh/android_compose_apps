package work.curioustools.composerecipes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import work.curioustools.composerecipes.network.models.CreateUserResponse
import work.curioustools.composerecipes.network.models.UserRequest
import work.curioustools.composerecipes.network.models.UserResponse
import work.curioustools.composerecipes.network.usecases.CreateUserUseCase
import work.curioustools.composerecipes.network.usecases.GetAllUsersUseCase
import work.curioustools.composerecipes.network.usecases.GetSingleUserUseCase
import work.curioustools.composerecipes.network.usecases.TestUseCase
import work.curioustools.composerecipes.network.usecases.UpdateUserUseCase
import work.curioustools.composerecipes.network.utils.ApiResponse
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject  constructor(
     private val getAllUsersUseCase: GetAllUsersUseCase,
     private val getSingleUserUseCase: GetSingleUserUseCase,
     private val createUserUseCase: CreateUserUseCase,
     private val updateUserUseCase: UpdateUserUseCase,
     private val testUseCase: TestUseCase
) : ViewModel() {
     // private val _allUsersState = mutableResponseState<List<UserResponse>>()
     private val _allUsersState = mutableStateOf<ApiResponse<List<UserResponse>>>(value = ApiResponse.Loading())
     val allUsersState: State<ApiResponse<List<UserResponse>>> = _allUsersState
     fun getAllUsers() {
          viewModelScope.launch {
               getAllUsersUseCase.requestForDataAsFlow(Unit).collect { _allUsersState.value = it }
          }
     }

     private val _singleUserState = mutableStateOf<ApiResponse<UserResponse>>(value = ApiResponse.Loading())
     val singleUserState: State<ApiResponse<UserResponse>> = _singleUserState
     fun getUser(id: Int) {
          viewModelScope.launch {
               getSingleUserUseCase.requestForDataAsFlow(id).collect { _singleUserState.value = it }
          }
     }

     private  val  _createRequestState = mutableStateOf<ApiResponse<CreateUserResponse>>(ApiResponse.Loading())
     val createRequestState:State<ApiResponse<CreateUserResponse>> = _createRequestState
     fun createUser(request: UserRequest) {
          viewModelScope.launch {
               createUserUseCase.requestForDataAsFlow(request).collect { _createRequestState.value = it }
          }
     }


     private  val  _updateRequestState = mutableStateOf<ApiResponse<CreateUserResponse>>(ApiResponse.Loading())
     val updateRequestState:State<ApiResponse<CreateUserResponse>> = _updateRequestState
     fun updateUser(request: UserRequest){
          viewModelScope.launch {
               updateUserUseCase.requestForDataAsFlow(request).collect { _updateRequestState.value = it }
          }
     }



}