package com.example.learnapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

@Suppress("DEPRECATION")
class Splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
               Handler().postDelayed({
                   val intent= Intent(this, SignUp::class.java)
                   startActivity(intent)
                   finish()
               },4000)


    }
}