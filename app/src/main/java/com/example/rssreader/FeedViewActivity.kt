package com.example.rssreader

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssreader.databinding.ActivityFeedViewBinding

class FeedViewActivity : AppCompatActivity() {

    var binding : ActivityFeedViewBinding? = null
    var rssUrlItemAdapter: RssUrlItemRecyclerViewAdapter? = null

    private val feedViewModel: FeedViewModel by viewModels {
        FeedViewModel.FeedViewModelFactory((application as RssItemsApplication).urlRepository)
    }

    // This activity gets a list of URLs from the viewModel.
    // viewmodel gets the urls from a repo
    // repo gets from the DB and different table than what I've used already

    // New viewModel, new Repo, new DB-table.
    // The new viewModel uses the new repo for getting the URLs from the database
    // View is done as... RecyclerView? Clicking the item shows a pop up and asks if the user
    // wants to delete it. Options: (Yes, No) / A button for deleting it on the item itself
    // is probably more clear.

    // A floating button etc. for adding a feed URL.
    // Opens a fragment that has    URL: WRITE HERE and Add button on it.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val toolbar : Toolbar = findViewById(R.id.myToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rssUrlItemAdapter = RssUrlItemRecyclerViewAdapter()
        binding?.urlRecyclerView?.adapter = rssUrlItemAdapter
        binding?.urlRecyclerView?.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(
            binding?.urlRecyclerView?.context,
            LinearLayoutManager.VERTICAL
        )
        binding?.urlRecyclerView?.addItemDecoration(divider)

        feedViewModel.allUrls.observe(this) {urls ->
            urls.let { rssUrlItemAdapter!!.submitList(it) }
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