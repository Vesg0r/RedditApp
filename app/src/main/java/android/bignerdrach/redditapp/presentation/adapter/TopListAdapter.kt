package android.bignerdrach.redditapp.presentation.adapter

import android.bignerdrach.redditapp.R
import android.bignerdrach.redditapp.data.response.Information
import android.bignerdrach.redditapp.data.response.TopList
import android.bignerdrach.redditapp.presentation.activities.ImageActivity
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils.getRelativeTimeSpanString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class TopListAdapter(private val topList: TopList) :
    RecyclerView.Adapter<TopListAdapter.ListHolder>() {
    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.image
        val author: TextView = itemView.text_author
        val time: TextView = itemView.text_time
        val title: TextView = itemView.text_title
        val comments: TextView = itemView.text_comments
        val commentsImage: ImageView = itemView.image_comments
        fun bind(listItem: Information) {
            image.setOnClickListener {
                val intent = Intent(itemView.context, ImageActivity::class.java)
                    .putExtra("url", listItem.url)
                startActivity(itemView.context, intent, Bundle.EMPTY)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ListHolder(itemView)
    }

    override fun getItemCount(): Int = topList.data.posts.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val listItem = topList.data.posts[position]
        holder.bind(listItem.data)
        Glide.with(holder.image.context).load(topList.data.posts[position].data.thumbnail)
            .into(holder.image)

        holder.commentsImage.setImageResource(R.drawable.comment)
        holder.author.text = topList.data.posts[position].data.author
        holder.title.text = topList.data.posts[position].data.title
        val commentsText = "${topList.data.posts[position].data.numComments} comments"
        holder.comments.text = commentsText
        holder.time.text =
            getRelativeTimeSpanString(topList.data.posts[position].data.createdUtc * 1000).toString()
    }
}