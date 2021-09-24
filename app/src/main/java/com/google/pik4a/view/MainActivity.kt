package com.google.pik4a.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.pik4a.R
import com.google.pik4a.view.picture.PODFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, PODFragment.newInstance()).commit()
        }


    }

}