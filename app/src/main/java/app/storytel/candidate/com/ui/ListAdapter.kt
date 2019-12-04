package app.storytel.candidate.com.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.R
import app.storytel.candidate.com.data.model.PostAndImages
import app.storytel.candidate.com.ui.ListAdapter.PostViewHolder
import com.bumptech.glide.Glide
import java.util.*

class ListAdapter(private val mActivity: Activity) : RecyclerView.Adapter<PostViewHolder>() {

    private var mData: PostAndImages = PostAndImages(mutableListOf(), mutableListOf())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = mData.posts[position].title
        holder.body.text = mData.posts[position].body
        val index = Random().nextInt(mData.photos.size - 1)
        val imageUrl = mData.photos[index].thumbnailUrl
        Glide.with(mActivity).load(imageUrl).into(holder.image)
        holder.body.setOnClickListener {
            mActivity.startActivity(Intent(mActivity, DetailActivity::class.java))
        }
    }

    fun setData(data: PostAndImages) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.posts.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var body: TextView = itemView.findViewById(R.id.body)
        var image: ImageView = itemView.findViewById(R.id.image)
    }

    /*class DiffCallback : DiffUtil.ItemCallback<Place>() {
            override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.alias == newItem.alias
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }*/
}