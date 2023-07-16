package com.example.rssreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssreader.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    lateinit var rssItemAdapter: RssItemRecyclerViewAdapter

    private val rssItemViewModel: RssItemViewModel by viewModels {
        RssItemViewModel.RssViewModelFactory((application as RssItemsApplication).repository)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        rssItemViewModel.getFeed()
        rssItemViewModel.allItems.observe(this) { items ->
            items?.let {
                // Have to null it first so emptyRecyclerViewDataObserver gets the change. Not sure what
                // happens under the hood.
                rssItemAdapter.submitList(null)
                rssItemAdapter.submitList(it) }
            val emptyRecyclerViewDataObserver = EmptyRecyclerViewDataObserver(binding?.recyclerView, binding?.emptyFeedView?.root)
            rssItemAdapter.registerAdapterDataObserver(emptyRecyclerViewDataObserver)
            binding?.recyclerView?.scrollToPosition(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val toolbar : Toolbar = findViewById(R.id.myToolbar)
        setSupportActionBar(toolbar)


        rssItemAdapter = RssItemRecyclerViewAdapter(applicationContext)
        binding?.recyclerView?.adapter = rssItemAdapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(
            binding?.recyclerView?.context,
            LinearLayoutManager.VERTICAL
        )
        binding?.recyclerView?.addItemDecoration(divider)
    }

    fun openFeedActivity(item: MenuItem) {
        val intent = Intent(this, FeedViewActivity::class.java)
        startActivity(intent)
    }
}

