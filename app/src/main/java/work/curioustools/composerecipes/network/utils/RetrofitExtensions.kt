package work.curioustools.composerecipes.network.utils

import retrofit2.Response

fun <T> Response<BackendJsonStructure<T>>.toApiResponse() : ApiResponse<T> {
    val body: BackendJsonStructure<T>? = body()
    if (isSuccessful && body != null && body.data!=null) {
        return ApiResponse.Success(
            data = body.data,
            page = body.page,
            perPage = body.per_page,
            total = body.total,
            totalPages = body.total_pages
        )
    }
    return ApiResponse.Failure(code(), errorMsg = body?.error)

}
