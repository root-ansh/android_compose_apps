package work.curioustools.composerecipes.network.utils

import work.curioustools.composerecipes.network.utils.ResponseCodeType.UNRECOGNISED


// main class that represents the data returned by api after initial parsing
sealed class ApiResponse<T> {
    data class Success<T>(val data: T, val page: Int = -1, val perPage: Int = -1, val total: Int = -1, val totalPages: Int = -1) : ApiResponse<T>()
    data class Failure<T>(val errorCode: Int, val errorMsg: String? = null ) : ApiResponse<T>(){
        fun errorCodeType() = ResponseCodeType.values().firstOrNull { it.code==errorCode }?: UNRECOGNISED
    }
}

