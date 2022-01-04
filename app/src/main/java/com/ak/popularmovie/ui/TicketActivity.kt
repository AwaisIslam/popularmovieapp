package com.ak.popularmovie.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ak.popularmovie.R
import com.ak.popularmovie.databinding.ActivityTicketBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class TicketActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
        binding.btnSelectSeat.setOnClickListener {
            val seatDetailIntent = Intent(this,SeatDetailsActivity::class.java)
            startActivity(seatDetailIntent)
        }
    }

    private fun setUpToolbar(){
        setSupportActionBar(binding.toolBarTicket)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_black)
        supportActionBar?.title = ""
    }
}