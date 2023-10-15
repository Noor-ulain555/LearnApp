package com.example.learnapp

import Data.propertyEntity
import Database.propertyDatabase
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnapp.databinding.ActivityPropertyDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class propertyDetail : AppCompatActivity() {
    private lateinit var binding: ActivityPropertyDetailBinding
    lateinit var Database: propertyDatabase
    lateinit var area:String
    private lateinit var purpose: String
    private lateinit var interior: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Spinner
        val options = arrayOf("Marla 3", "Marla 5", "Marla 7","Marla 10")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                area = options[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        checkFurnished()
        checkPurpose()


        binding.saveData.setOnClickListener {

            Database = propertyDatabase.getDatabase(this)


                val room = binding.room.text.toString()
                val BR = binding.bedroom.text.toString()
                val BadR = binding.bathroom.text.toString()
                val floor = binding.floor.text.toString()
                val city = binding.city.text.toString()
                val location = binding.location.text.toString()



            CoroutineScope(Dispatchers.IO).launch {
                val user = propertyEntity(0, room, BR, BadR, floor, city, location,
                    checkFurnished().toString(),area,checkPurpose().toString())
                Database.propertyDao().insert(user)
            }
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
        finish()
            startActivity(Intent(this, propertyInfo::class.java))

        }
    }

    private fun checkFurnished() {
        binding.InteriorGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbFurnished -> {
                    interior="Furnished"
                }
                R.id.rbNonFurnished -> {
                    interior="Non Furnished"
                }
            }
        }
    }
    private fun checkPurpose() {
        binding.PurposeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbForSale -> {
                    purpose = "Sale"
                }

                R.id.rbForRent -> {
                    purpose = "Rent"
                }
            }
        }
    }
}
