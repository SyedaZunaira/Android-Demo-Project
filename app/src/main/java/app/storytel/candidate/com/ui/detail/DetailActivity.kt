package app.storytel.candidate.com.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.ActivityDetailsBinding
import app.storytel.candidate.com.utils.AppConstants.IMAGE_KEY
import app.storytel.candidate.com.utils.AppConstants.POST_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailViewModel by viewModel<DetailViewModel>()

        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this

        detailViewModel.setData(intent.getStringExtra(IMAGE_KEY), intent.getParcelableExtra(POST_KEY))
        binding.executePendingBindings()
    }
}
