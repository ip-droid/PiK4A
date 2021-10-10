package com.google.pik4a.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
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



        val builder = GuideView.Builder(this)
            .setTitle("ВНИМАНИЕ! ПЛОХОЙ ПОДХОД")
            .setContentText("Здесь мы выделили ВАЖНУЮ кнопку размером текста, так лучше не делать")
            .setGravity(Gravity.center)
            .setDismissType(DismissType.selfView)
            .setTargetView(binding.btnTestBad)
            .setDismissType(DismissType.anywhere)
            .setGuideListener(object : GuideListener {
                override fun onDismiss(view: View?) {
                    val builder = GuideView.Builder(this@TestActivity)
                        .setTitle("ВНИМАНИЕ! ПРАВИЛЬНЫЙ ПОДХОД")
                        .setContentText("Здесь мы выделили ВАЖНУЮ кнопку стилем OutlinedButton, это хорошая практика")
                        .setGravity(Gravity.center)
                        .setDismissType(DismissType.selfView)
                        .setTargetView(binding.btnTestGood)
                        .setDismissType(DismissType.anywhere)
                        .setGuideListener(object : GuideListener {
                            override fun onDismiss(view: View?) {

                            }
                        })
                    builder.build().show()
                }
            })
        builder.build().show()


    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}