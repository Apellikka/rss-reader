package com.example.rssreader.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssreader.*
import com.example.rssreader.adapters.RssUrlItemRecyclerViewAdapter
import com.example.rssreader.database.RssItemsApplication
import com.example.rssreader.databinding.ActivityFeedViewBinding
import com.example.rssreader.models.FeedViewModel
import com.example.rssreader.ui.fragment.DeleteUrlDialogFragment
import com.example.rssreader.ui.fragment.NewUrlDialogFragment
import com.example.rssreader.utils.EmptyRecyclerViewDataObserver


class FeedViewActivity : AppCompatActivity() {

    var binding : ActivityFeedViewBinding? = null
    lateinit var rssUrlItemAdapter: RssUrlItemRecyclerViewAdapter

    private val feedViewModel: FeedViewModel by viewModels {
        FeedViewModel.FeedViewModelFactory((application as RssItemsApplication).urlRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val toolbar : Toolbar = findViewById(R.id.myToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rssUrlItemAdapter = RssUrlItemRecyclerViewAdapter()
        rssUrlItemAdapter?.apply {
            onUrlClick = {
                DeleteUrlDialogFragment(rssUrlItemAdapter.getUrl()).show(supportFragmentManager,
                    DeleteUrlDialogFragment.TAG
                )
            }
        }

        binding?.urlRecyclerView?.adapter = rssUrlItemAdapter
        binding?.urlRecyclerView?.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(
            binding?.urlRecyclerView?.context,
            LinearLayoutManager.VERTICAL
        )
        binding?.urlRecyclerView?.addItemDecoration(divider)
        divider.setDrawable(ResourcesCompat.getDrawable(resources, android.R.color.white, theme)!!)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            NewUrlDialogFragment().show(supportFragmentManager, NewUrlDialogFragment.TAG)
        }
    }

    override fun onResume() {
        super.onResume()

        feedViewModel.allUrls.observe(this) {urls ->
            urls.let {
                // Have to null it first so emptyRecyclerViewDataObserver gets the change. Not sure what
                // happens under the hood.
                rssUrlItemAdapter.submitList(null)
                rssUrlItemAdapter.submitList(it) }
            val emptyRecyclerViewDataObserver = EmptyRecyclerViewDataObserver(binding?.urlRecyclerView, binding?.emptyUrlView?.root)
            rssUrlItemAdapter.registerAdapterDataObserver(emptyRecyclerViewDataObserver)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return true
    }
}