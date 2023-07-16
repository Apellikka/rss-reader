package com.example.rssreader

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class DeleteUrlDialogFragment(var urlItem : RssUrlItem?) : DialogFragment() {

    private val viewModel: FeedViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireActivity())
            .setTitle("Delete selected url?")
            .setPositiveButton("Delete url") { _, _ ->
                deleteUrl(urlItem)
            }
            .setNegativeButton("Cancel", null)
            .create()

    companion object {
        const val TAG = "DeleteUrlDialogFragment"
    }

    private fun deleteUrl(urlItem : RssUrlItem?) {
        println(urlItem?.url)
        viewModel.deleteUrl(urlItem!!)
    }
}