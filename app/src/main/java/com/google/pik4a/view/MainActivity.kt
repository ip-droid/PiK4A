package com.google.pik4a.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.pik4a.R
import com.google.pik4a.homework_three.NasaFragment
import com.google.pik4a.view.picture.PODFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_bottom)



        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, NasaFragment.newInstance()).commit()
        }
    }

}