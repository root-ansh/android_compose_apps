package work.curioustools.composerecipes.network.repos

import work.curioustools.composerecipes.network.utils.ApiResponse
import work.curioustools.composerecipes.network.models.CreateUserRequest
import work.curioustools.composerecipes.network.models.CreateUserResponse
import work.curioustools.composerecipes.network.models.UserResponse

interface UserRepo {
    suspend fun getUserList(): ApiResponse<List<UserResponse>>
    suspend fun getUser(id: Int): ApiResponse<UserResponse>
    suspend fun createUser(data: CreateUserRequest): ApiResponse<CreateUserResponse>
    suspend fun updateUser(data: CreateUserRequest) : ApiResponse<CreateUserResponse>
}

