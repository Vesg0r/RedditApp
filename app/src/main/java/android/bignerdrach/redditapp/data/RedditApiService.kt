package android.bignerdrach.redditapp.data

import android.bignerdrach.redditapp.data.response.TopList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://www.reddit.com/top.json
const val BASE_URL = "https://www.reddit.com/"

interface RedditApiService {
    @GET("top.json")
    fun getTopList(): Call<TopList>

    companion object {
        operator fun invoke() : RedditApiService{
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RedditApiService::class.java)
        }
    }
}