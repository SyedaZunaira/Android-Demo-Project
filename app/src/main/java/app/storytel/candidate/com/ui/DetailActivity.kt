package app.storytel.candidate.com.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.storytel.candidate.com.R
import app.storytel.candidate.com.base.BaseApplication.Companion.getAppInjector
import app.storytel.candidate.com.databinding.ActivityDetailsBinding
import app.storytel.candidate.com.utils.AppConstants.IMAGE_KEY
import app.storytel.candidate.com.utils.AppConstants.POST_KEY
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getAppInjector().inject(this)

        val detailViewModel by lazy { ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java) }

        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this

        detailViewModel.setData(intent.getStringExtra(IMAGE_KEY), intent.getParcelableExtra(POST_KEY))
        binding.executePendingBindings()
    }
}
