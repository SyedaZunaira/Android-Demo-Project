package app.storytel.candidate.com.utils

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.R
import app.storytel.candidate.com.data.model.PostAndImages
import app.storytel.candidate.com.ui.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: PostAndImages?) {
    val adapter = recyclerView.adapter as ListAdapter
    data?.let {
        adapter.setData(data)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
                .load(imgUrl)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
    }
}

@BindingAdapter("api_status")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("android:visibility")
fun bindRetryButtonVisibility(button: Button, status: ApiStatus?) {
    when (status) {
        ApiStatus.ERROR -> button.visibility = View.VISIBLE
        else -> button.visibility = View.GONE
    }
}

@BindingAdapter("android:visibility")
fun bindNestedResyclerVisibility(view: NestedScrollView, status: ApiStatus?) {
    when (status) {
        ApiStatus.ERROR -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}
