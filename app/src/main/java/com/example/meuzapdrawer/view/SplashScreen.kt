package com.example.meuzapdrawer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.meuzapdrawer.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed ({
            val i = Intent(this, Splash::class.java)
            startActivity(i)
            finish()
        },2000)
    }
}