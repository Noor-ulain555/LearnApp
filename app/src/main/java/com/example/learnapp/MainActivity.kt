package com.example.learnapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.profileBtn.setOnClickListener {
            startActivity(Intent(this, Profile_Screen::class.java))
        }
        binding.userBtn.setOnClickListener {
            startActivity(Intent(this, UserCall_screen::class.java))
        }
        binding.PropertyDetailBtn.setOnClickListener {
            startActivity(Intent(this, propertyDetail::class.java))
        }
        binding.detailBtn.setOnClickListener {
            startActivity(Intent(this, propertyInfo::class.java))
        }
    }
}