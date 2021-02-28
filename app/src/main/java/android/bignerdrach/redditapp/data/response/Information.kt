package android.bignerdrach.redditapp.data.response

import com.google.gson.annotations.SerializedName

data class Information(
    val author: String,
    @SerializedName("created_utc")
    val createdUtc: Long,
    @SerializedName("num_comments")
    val numComments: Int,
    val thumbnail: String,
    val title: String,
    val url: String
)