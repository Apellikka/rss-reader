package com.example.rssreader

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssreader.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var rssItemAdapter: RssItemRecyclerViewAdapter? = null

    private val rssItemViewModel: RssItemViewModel by viewModels {
        RssItemViewModel.RssViewModelFactory((application as RssItemsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        rssItemAdapter = RssItemRecyclerViewAdapter()
        binding?.recyclerView?.adapter = rssItemAdapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(binding?.recyclerView?.context,
            LinearLayoutManager.VERTICAL)
        binding?.recyclerView?.addItemDecoration(divider)

        rssItemViewModel.allItems.observe(this) { items ->
            items?.let { rssItemAdapter!!.submitList(it) }
        }
    }


}

