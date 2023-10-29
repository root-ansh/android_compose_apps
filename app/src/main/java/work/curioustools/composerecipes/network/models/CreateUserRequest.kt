package work.curioustools.composerecipes.network.models

data class CreateUserRequest(
    val name:String,
    val job:String,
    val id:Int? = null
)