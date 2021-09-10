package com.google.pik4a.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextThemeWrapper
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

        class MyButton : MaterialButton {
            constructor(
                context: Context,
                attrs: AttributeSet
            ) : super(
                // It will override the contexts theme with the values inside theme overlay.
                ContextThemeWrapper(context, R.style.Theme_Martian),
                attrs
            )
        }
    }



}