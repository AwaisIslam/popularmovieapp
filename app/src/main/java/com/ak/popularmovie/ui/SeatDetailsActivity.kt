package com.ak.popularmovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ak.popularmovie.R
import com.ak.popularmovie.databinding.ActivitySeatDetailsBinding

class SeatDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySeatDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
    }
    private fun setUpToolbar(){
        setSupportActionBar(binding.toolBarSeatDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_black)
        supportActionBar?.title = ""
    }
}