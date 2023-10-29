package work.curioustools.composerecipes.network.usecases

import org.json.JSONArray
import org.json.JSONObject
import work.curioustools.composerecipes.commons.log
import work.curioustools.composerecipes.network.models.UserRequest
import work.curioustools.composerecipes.network.models.CreateUserResponse
import work.curioustools.composerecipes.network.models.UserResponse
import work.curioustools.composerecipes.network.repos.UserRepo
import work.curioustools.composerecipes.network.utils.ApiResponse
import work.curioustools.composerecipes.network.utils.BaseConcurrencyUseCase
import java.util.UUID
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val repo: UserRepo) :
    BaseConcurrencyUseCase<Unit, ApiResponse<List<UserResponse>>>() {
    override suspend fun getRepoCall(param: Unit): ApiResponse<List<UserResponse>> {
        return repo.getUserList()
    }
}

class GetSingleUserUseCase @Inject constructor( private val repo: UserRepo) :
    BaseConcurrencyUseCase<Int, ApiResponse<UserResponse>>() {
    override suspend fun getRepoCall(param: Int): ApiResponse<UserResponse> {
        return repo.getUser(param)
    }
}

class UpdateUserUseCase @Inject constructor( private val repo: UserRepo) :
    BaseConcurrencyUseCase<UserRequest, ApiResponse<CreateUserResponse>>() {
    override suspend fun getRepoCall(param: UserRequest): ApiResponse<CreateUserResponse> {
        return repo.updateUser(param)
    }
}

class CreateUserUseCase @Inject constructor( private val repo: UserRepo) :
    BaseConcurrencyUseCase<UserRequest, ApiResponse<CreateUserResponse>>() {
    override suspend fun getRepoCall(param: UserRequest): ApiResponse<CreateUserResponse> {
        return repo.createUser(param)
    }
}

class TestUseCase @Inject constructor() : BaseConcurrencyUseCase<Int, JSONObject>() {
    override suspend fun getRepoCall(param: Int): JSONObject {
        log("getRepoCall: called")
        val json = JSONObject()
        val data = JSONArray()
        repeat(param * 10) {
            val obj = JSONObject()
            obj.put("id","${it+1}")
            obj.put("name", UUID.randomUUID()?.toString()?.replace("-", "")?.substring(0..8) ?: "12345678")
            data.put(obj)
        }
        json.put("data",data)
        return json

    }
}
