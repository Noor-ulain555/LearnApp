package com.example.learnapp

import Data.DataEntity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnapp.databinding.ActivityUserCallScreenBinding
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserCall_screen : AppCompatActivity() {
    private lateinit var binding: ActivityUserCallScreenBinding
    private lateinit var adapter: RvAdapter
    private lateinit var database: databaseRoom
    private val scope=CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LearnApp)
        binding = ActivityUserCallScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= Room.databaseBuilder(applicationContext,databaseRoom::class.java,"signUpDB").build()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        scope.launch{
            val users: List<DataEntity> =database.dbDao().getsignUp()
        withContext(Dispatchers.Main){
            withContext(Dispatchers.Main){
                val adapter=RvAdapter(applicationContext,users)
                binding.recyclerView.adapter=adapter
                adapter.notifyDataSetChanged()
            }
        }
        }
        }
    }


