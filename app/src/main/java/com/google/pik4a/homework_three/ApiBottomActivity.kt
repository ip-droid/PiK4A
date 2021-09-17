package com.google.pik4a.homework_three

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.badge.BadgeDrawable
import com.google.pik4a.R
import com.google.pik4a.databinding.ActivityApiBottomBinding

class ApiBottomActivity : AppCompatActivity() {

    lateinit var binding: ActivityApiBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.action_mars->{
                    supportFragmentManager.beginTransaction().replace(R.id.container, MarsFragment()).commit()
                    true
                }
                R.id.action_earth->{
                    supportFragmentManager.beginTransaction().replace(R.id.container, EarthFragment()).commit()
                    true
                }
                R.id.action_system->{
                    supportFragmentManager.beginTransaction().replace(R.id.container, SystemFragment()).commit()
                    true
                }
                else ->false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.action_system
        binding.bottomNavigationView.getOrCreateBadge(R.id.action_earth).apply {
            number = 100;
            badgeGravity = BadgeDrawable.TOP_END
            maxCharacterCount = 3
        }
        binding.bottomNavigationView.menu.getItem(1).apply{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tooltipText = "Это марс"
            }
        }
    }

}