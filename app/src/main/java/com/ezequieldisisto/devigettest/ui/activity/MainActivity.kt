package com.ezequieldisisto.devigettest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ezequieldisisto.devigettest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun showBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }
}
