package com.google.pik4a.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.google.pik4a.databinding.ActivitySplashBinding
import com.google.pik4a.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    lateinit var handler: Handler
    lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val themeID = getMyTheme();
        setTheme(themeID)*/
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}