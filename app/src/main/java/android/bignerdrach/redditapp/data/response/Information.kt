package android.bignerdrach.redditapp.data.response

import com.google.gson.annotations.SerializedName

data class Information(
    val author: String,
    @SerializedName("created_utc")
    val createdUtc: Long,
    val id: String,
    @SerializedName("num_comments")
    val numComments: Int,
    val thumbnail: String,
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Int,
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Int,
    val title: String,
    val url: String
)