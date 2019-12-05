package app.storytel.candidate.com.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.storytel.candidate.com.R
import app.storytel.candidate.com.base.BaseApplication.Companion.getAppInjector
import app.storytel.candidate.com.databinding.ActivityScrollingBinding
import com.bumptech.glide.Glide
import javax.inject.Inject

class ListActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityScrollingBinding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        getAppInjector().inject(this)

        val masterViewModel by lazy { ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java) }
        val adapter = ListAdapter(Glide.with(this), this)

        binding.masterViewModel = masterViewModel
        binding.recyclerView.adapter = adapter
        binding.lifecycleOwner = this

        masterViewModel.postAndImageLiveData.observe(
                this,
                Observer {
                    it?.let {
                        adapter.setData(it)
//                        adapter.submitList(it)
                    }
                })
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