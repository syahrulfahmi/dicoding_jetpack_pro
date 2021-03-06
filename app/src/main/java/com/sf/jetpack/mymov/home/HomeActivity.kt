package com.sf.jetpack.mymov.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sf.jetpack.mymov.databinding.ActivityHomeBinding
import com.sf.jetpack.mymov.ui.main.SectionsPagerAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.apply {
            adapter = sectionsPagerAdapter
            binding.tabs.setupWithViewPager(this)
        }

    }
}