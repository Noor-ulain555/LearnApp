package com.example.learnapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnapp.databinding.ActivityCalculateAgeBinding
import java.util.Calendar

class calculateAge : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateAgeBinding
    private var mDate: Int = 0
    private var mMonth: Int = 0
    private var mYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.datePicker.setOnClickListener(View.OnClickListener {
            val cal = Calendar.getInstance()
            mDate = cal.get(Calendar.DATE)
            mMonth = cal.get(Calendar.MONTH)
            mYear = cal.get(Calendar.YEAR)

            val datePickerDialog = DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    binding.Showdate.text = "${dayOfMonth}-${ month + 1 }-$year"
                }, mYear, mMonth, mDate
            )
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 1000
            datePickerDialog.show()
        })

        binding.showAgeBtn.setOnClickListener(View.OnClickListener {
            val selectedDate = binding.Showdate.text.toString()
            if (selectedDate.isNotEmpty()) {
                val parts = selectedDate.split("-")
                val year = parts[2].toInt()
                val month = parts[1].toInt() - 1 // Subtract 1 to match Calendar.MONTH
                val dayOfMonth = parts[0].toInt()

                val age = calculateAge(year, month, dayOfMonth)

                // Display the age in years, months, and days
                binding.AgeShowTv.text = "Age: ${age.years} years, ${age.months} months, ${age.days} days"
            } else {
                // Handle the case where no date is selected
                binding.AgeShowTv.text = "Please select a date of birth"
            }
        })
    }

    private fun calculateAge(selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int): Age {
        val currentDate = Calendar.getInstance()
        val birthDate = Calendar.getInstance()
        birthDate.set(selectedYear, selectedMonth, selectedDayOfMonth)

        var years = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
        var months = currentDate.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH)
        var days = currentDate.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH)

        if (days < 0) {
            val prevMonthLastDay = birthDate.getActualMaximum(Calendar.DAY_OF_MONTH)
            days += prevMonthLastDay
            months--
        }
        if (months < 0) {
            months += 12
            years--
        }
        return Age(years, months, days)
    }

    data class Age(val years: Int, val months: Int, val days: Int)
}
