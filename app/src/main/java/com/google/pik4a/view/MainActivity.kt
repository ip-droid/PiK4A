package com.google.pik4a.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.button.MaterialButton
import com.google.pik4a.R
import com.google.pik4a.view.picture.PODFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, PODFragment.newInstance()).commit()
        }
    }
}