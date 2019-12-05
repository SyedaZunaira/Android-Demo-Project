package app.storytel.candidate.com.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.ActivityDetailsBinding
import app.storytel.candidate.com.utils.AppConstants.IMAGE_KEY
import app.storytel.candidate.com.utils.AppConstants.POST_KEY

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.imageUrl = intent.getStringExtra(IMAGE_KEY)
        binding.post = intent.getParcelableExtra(POST_KEY)
        // TODO display the selected post from ScrollingActivity. Use mImageView and mTextView for image and body text. Change the title to use the post title
        // TODO load top 3 comments from COMMENTS_URL into the 3 card views
    }
}