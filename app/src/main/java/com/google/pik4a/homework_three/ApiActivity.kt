package com.google.pik4a.homework_three

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.pik4a.R
import com.google.pik4a.databinding.ActivityApiBinding

class ApiActivity : AppCompatActivity() {

    lateinit var binding: ActivityApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        /* binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_earth)
         binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_mars)
         binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_system)*/

        /*val viewEarth = layoutInflater.inflate(R.layout.activity_api_tablayout_item,null)
        viewEarth.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.textView).apply {
            text = "Earth"
        }*/

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