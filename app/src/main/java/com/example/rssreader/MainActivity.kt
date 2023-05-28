package com.example.rssreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rssreader.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    val api: API? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }


}

