package com.example.learnapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.learnapp.databinding.ActivityBmiBinding


class BMI : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
                    binding.BMIBtn.setOnClickListener {
                    val wt = binding.weightText.text.toString().toInt()
                    val ft = binding.heightText.text.toString().toInt()
                    val inch = binding.incheText.text.toString().toInt()
                    val totalInch = ft * 12 + inch
                    val totalCm = totalInch * 2.54
                    val totalM = totalCm / 100
                    val BMI = wt / (totalM * totalM)
                    if (BMI > 25) {
                        binding.result.text = "You are overweight"
                        val color = ContextCompat.getColor(this, R.color.colorOW)
                        binding.llmain.setBackgroundColor(color)
                    } else if (BMI < 18) {
                        binding.result.text = "You are underweight"
                        val color = ContextCompat.getColor(this, R.color.colorUW)
                        binding.llmain.setBackgroundColor(color)
                    } else {
                        binding.result.text = "You are healthy"
                        val color = ContextCompat.getColor(this, R.color.colorHW)
                        binding.llmain.setBackgroundColor(color)
                    }
                }
            }
        }
