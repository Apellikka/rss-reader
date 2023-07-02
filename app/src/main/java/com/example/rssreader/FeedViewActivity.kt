package com.example.rssreader

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.rssreader.databinding.ActivityFeedViewBinding

class FeedViewActivity : AppCompatActivity() {

    var binding : ActivityFeedViewBinding? = null
    var urlList : ArrayList<String>? = null

    // This activity gets a list of URLs from the viewModel.

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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return true
    }
}