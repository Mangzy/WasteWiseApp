package com.example.wastewise.ui.detail_booklet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wastewise.R

class DetailBookletActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_booklet)
    }

    companion object{
        const val EXTRA_ID = "extra_id"
    }
}