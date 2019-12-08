package app.storytel.candidate.com.ui.list

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.data.model.Post
import app.storytel.candidate.com.data.model.PostAndImages
import app.storytel.candidate.com.databinding.ListItemBinding
import app.storytel.candidate.com.ui.detail.DetailActivity
import app.storytel.candidate.com.utils.AppConstants.IMAGE_KEY
import app.storytel.candidate.com.utils.AppConstants.POST_KEY
import java.util.*

class ListAdapter(private val activity: Activity) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var data: PostAndImages = PostAndImages(emptyList(), emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = data.posts[position]
        val index = Random().nextInt(data.photos.size - 1)
        val imageUrl = data.photos[index].thumbnailUrl
        holder.bind(post, imageUrl)
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(POST_KEY, post)
            intent.putExtra(IMAGE_KEY, imageUrl)
            activity.startActivity(intent)
        }
    }

    fun setData(data: PostAndImages) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.posts.size
    }

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post, imageUrl: String) {
            binding.post = item
            binding.imageUrl = imageUrl
            binding.executePendingBindings()
        }
    }
}