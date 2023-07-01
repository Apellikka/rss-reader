package com.example.rssreader

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.rssreader.databinding.ActivityWebviewActivityBinding

class WebViewActivity : AppCompatActivity() {

    var binding : ActivityWebviewActivityBinding? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewActivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.webview?.webViewClient = WebViewClient()
        val url = intent.extras?.getString("url")
        binding?.webview?.loadUrl(url!!)
    }
}