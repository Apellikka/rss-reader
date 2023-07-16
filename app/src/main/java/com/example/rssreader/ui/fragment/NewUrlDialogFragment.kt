package com.example.rssreader.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.rssreader.models.FeedViewModel
import com.example.rssreader.R
import com.example.rssreader.models.RssUrlItem

class NewUrlDialogFragment : DialogFragment() {
    private val viewModel: FeedViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireActivity())
            .setView(R.layout.add_url)
            .setTitle("Add new feed")
            .setPositiveButton("Add url") { _, _ ->
                addNewUrl()
            }
            .setNegativeButton("Cancel", null)
            .create()

    companion object {
        const val TAG = "NewUrlDialogFragment"
    }

    private fun addNewUrl() {
        val url = dialog?.findViewById<EditText>(R.id.urlEditText)
        val urlItem = RssUrlItem(url?.text.toString())
        viewModel.insertUrl(urlItem)
    }
}