package work.curioustools.composerecipes.network.models

data class UserRequest(
    val name:String,
    val job:String,
    val id:Int? = null
)