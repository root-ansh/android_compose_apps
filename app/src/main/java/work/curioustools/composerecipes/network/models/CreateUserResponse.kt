package work.curioustools.composerecipes.network.models

data class CreateUserResponse(
    val name:String,
    val job:String,
    val id:String,
    val createdAt:String? = null,
    val updatedAt:String? = null
)