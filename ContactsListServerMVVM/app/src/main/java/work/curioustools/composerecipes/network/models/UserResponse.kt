package work.curioustools.composerecipes.network.models
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep


data class UserResponse(
    val avatar: String,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    val id: Int,
    @SerializedName("last_name") val lastName: String
)


