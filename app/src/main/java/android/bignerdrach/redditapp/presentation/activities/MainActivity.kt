package android.bignerdrach.redditapp.presentation.activities

import android.bignerdrach.redditapp.R
import android.bignerdrach.redditapp.data.RedditApiService
import android.bignerdrach.redditapp.data.response.TopList
import android.bignerdrach.redditapp.presentation.adapter.TopListAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mService: RedditApiService
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: TopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager = LinearLayoutManager(this)
        recyclerTopList.layoutManager = layoutManager
        mService = RedditApiService()
        getAllPostsList()
    }

    private fun getAllPostsList() {
        mService.getTopList().enqueue(object : Callback<TopList> {
            override fun onFailure(call: Call<TopList>, t: Throwable) {
                Toast.makeText(baseContext, "Exception", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<TopList>, response: Response<TopList>) {
                if (response.isSuccessful) {
                    adapter = TopListAdapter(response.body() as TopList)
                    adapter.notifyDataSetChanged()
                    recyclerTopList.adapter = adapter
                } else {
                    onFailure(call, Exception())
                }
            }
        })
    }
}