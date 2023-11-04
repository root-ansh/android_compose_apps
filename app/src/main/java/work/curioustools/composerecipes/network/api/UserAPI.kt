package work.curioustools.composerecipes.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import work.curioustools.composerecipes.network.utils.BackendJsonStructure
import work.curioustools.composerecipes.network.models.UserRequest
import work.curioustools.composerecipes.network.models.CreateUserResponse
import work.curioustools.composerecipes.network.models.UserResponse

interface UserAPI {
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") pageNum: Int? = null,
        @Query("per_page") perPage: Int? = null,
    ): Response<BackendJsonStructure<List<UserResponse>>>

    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<BackendJsonStructure<UserResponse>>


    @POST("api/users")
    suspend fun createUser(@Body data: UserRequest): Response<BackendJsonStructure<CreateUserResponse>>

    @PATCH("api/users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body data: UserRequest
    ): Response<BackendJsonStructure<CreateUserResponse>>



}