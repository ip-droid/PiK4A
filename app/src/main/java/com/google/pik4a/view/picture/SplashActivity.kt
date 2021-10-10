package com.google.pik4a.view.picture

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.pik4a.R
import com.google.pik4a.databinding.ActivitySplashBinding
import com.google.pik4a.homework_three.NasaFragment
import com.google.pik4a.view.MainActivity
import com.google.pik4a.view.picture.PODFragment

class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val themeID = getMyTheme();
        setTheme(themeID)*/
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.animate().rotationBy(1000f).setInterpolator(LinearInterpolator()).duration = 3000
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}