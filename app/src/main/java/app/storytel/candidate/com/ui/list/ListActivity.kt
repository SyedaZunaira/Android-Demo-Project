package app.storytel.candidate.com.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.ActivityListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        val listViewModel by viewModel<ListViewModel>()

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