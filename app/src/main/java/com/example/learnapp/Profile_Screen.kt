package com.example.learnapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnapp.databinding.ActivityProfileScreenBinding

class Profile_Screen : AppCompatActivity() {
    private lateinit var binding:ActivityProfileScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.AgeBtn.setOnClickListener {
            startActivity(Intent(this,calculateAge::class.java))
        }
        binding.BmiBtn.setOnClickListener {
            startActivity(Intent(this,BMI::class.java))
        }
//        val editor=getSharedPreferences("flag", MODE_PRIVATE)
//        binding.name1.setText("password ${editor.getString("name",null)}")
//        binding.email1.setText("name ${editor.getString("email",null)}")
//        binding.phone1.setText("password ${editor.getString("password",null)}")
//        binding.password1.setText("password ${editor.getString("password",null)}")





    }
}