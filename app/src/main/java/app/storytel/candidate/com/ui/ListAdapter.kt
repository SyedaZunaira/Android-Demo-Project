package app.storytel.candidate.com.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.data.model.Post
import app.storytel.candidate.com.data.model.PostAndImages
import app.storytel.candidate.com.databinding.PostItemBinding
import com.bumptech.glide.RequestManager
import java.util.*

class ListAdapter(private val requestManager: RequestManager, private val mActivity: Activity) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var data: PostAndImages = PostAndImages(mutableListOf(), mutableListOf())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = data.posts[position]
        val index = Random().nextInt(data.photos.size - 1)
        val imageUrl = data.photos[index].thumbnailUrl
        holder.bind(post, imageUrl)
        holder.itemView.setOnClickListener {
            mActivity.startActivity(Intent(mActivity, DetailActivity::class.java))
        }
    }

    fun setData(data: PostAndImages) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.posts.size
    }


    inner class ViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post, imageUrl: String) {
            binding.post = item
            requestManager.load(imageUrl).into(binding.image)
            binding.executePendingBindings()
        }
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