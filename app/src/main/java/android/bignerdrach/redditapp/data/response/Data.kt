package android.bignerdrach.redditapp.data.response

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("children")
    val posts: List<Post>
)