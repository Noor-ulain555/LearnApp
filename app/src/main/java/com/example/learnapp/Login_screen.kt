package com.example.learnapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnapp.databinding.ActivityLoginScreenBinding

class Login_screen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)

        binding.email.setText(sharedPreferences.getString("email", null))
        binding.password.setText(sharedPreferences.getString("password", null))

        val check = sharedPreferences.getBoolean("flag", false)

        val nextIntent = if (check) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, Login_screen::class.java)
        }

        binding.login.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val editor = sharedPreferences.edit()
            editor.putBoolean("flag", true)
            editor.putString("email", email)
            editor.putString("password", password)
            editor.apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.tvlogOut.setOnClickListener {
            val pref: SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = pref.edit()
            editor.putBoolean("flag", false)
            editor.apply()

        }
    }
}
