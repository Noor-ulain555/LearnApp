package com.example.learnapp

import Database.propertyDatabase
import DetailAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnapp.databinding.ActivityPropertyInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class propertyInfo : AppCompatActivity() {
    private lateinit var binding: ActivityPropertyInfoBinding
    lateinit var Database: propertyDatabase

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_info)
        binding = ActivityPropertyInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Database = propertyDatabase.getDatabase(this)
        binding.showDetailRv.layoutManager = LinearLayoutManager(this)
            CoroutineScope(Dispatchers.IO).launch {
                val detailusers = Database.propertyDao().getAllProperties()
                withContext(Dispatchers.Main) {
                    val userDetailAdaptor = DetailAdapter(applicationContext, detailusers)
                    binding.showDetailRv.adapter = userDetailAdaptor
                    userDetailAdaptor.notifyDataSetChanged()
                }
            }
            }

        }
