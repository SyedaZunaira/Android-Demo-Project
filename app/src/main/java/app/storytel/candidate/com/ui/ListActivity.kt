package app.storytel.candidate.com.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import app.storytel.candidate.com.R
import app.storytel.candidate.com.base.BaseApplication.Companion.getAppInjector
import app.storytel.candidate.com.databinding.ActivityListBinding
import javax.inject.Inject

class ListActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        getAppInjector().inject(this)

        val listViewModel by lazy { ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java) }

        binding.viewModel = listViewModel
        binding.recyclerView.adapter = ListAdapter(this)
        binding.lifecycleOwner = this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}