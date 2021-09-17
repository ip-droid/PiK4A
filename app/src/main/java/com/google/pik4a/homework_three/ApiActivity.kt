package com.google.pik4a.homework_three

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.pik4a.R
import com.google.pik4a.databinding.ActivityApiBinding

class ApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()

        binding.tabLayout.getTabAt(0)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tablayout_earth,null)
        binding.tabLayout.getTabAt(1)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tablayout_mars,null)
        binding.tabLayout.getTabAt(2)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tablayout_system,null)
        binding.tabLayout.getTabAt(3)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tablayout_nasa,null)


    }

}