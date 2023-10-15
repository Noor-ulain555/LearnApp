package com.example.learnapp

import Data.DataEntity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.learnapp.databinding.ActivitySignUpScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUp : AppCompatActivity() {
        private lateinit var binding: ActivitySignUpScreenBinding
        lateinit var database: databaseRoom
        private lateinit var sharedPreferences: SharedPreferences


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)

            database = databaseRoom.getDatabase(applicationContext)

            sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
            binding.email.setText(sharedPreferences.getString("email", null))
            val check = sharedPreferences.getBoolean("flag", false)


            val intent=  Intent(this, MainActivity::class.java)

            database = Room.databaseBuilder(
                applicationContext,
                databaseRoom::class.java,
                "signUpDB"
            ).build()

            binding.register.setOnClickListener {
                val name = binding.userName.text.toString()
                val email = binding.email.text.toString()
                val phone = binding.phone.text.toString()
                val password = binding.password.text.toString()

                if (Validation.nameValidation(name, binding.userName) &&
                    Validation.emailValidation(this, email, binding.email) &&
                    Validation.phoneValidation(this, phone, binding.phone) &&
                    Validation.passwordValidation(this, password, binding.password)
                ){
                    CoroutineScope(Dispatchers.IO).launch {
                        database.dbDao().insertData(
                            DataEntity(
                                name,
                                email,
                                phone,
                                password
                            )
                        )
                    }
                    Log.d("dataSaver", "dataaaaa")
                    startActivity(intent)
                }
            }
        }
        object Validation {
            fun nameValidation(name: String, edName: EditText): Boolean {
                if (!name.matches("^[a-zA-Z.\\s]+\$".toRegex())) {
                    edName.error = "Name Must Contain Only Alphabets"
                    return false
                }
                return true
            }

            fun phoneValidation(ignoredContext: Context, phone: String, edPhone: EditText): Boolean {
                if (phone.length != 11) {
                    edPhone.error = "Phone Number must be 11 characters"
                    return false
                }
                return true
            }

            fun emailValidation(context: Context,email: String, edEmail: EditText): Boolean {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edEmail.error = "Email format is not correct"
                    return false
                }
                return true
            }
            fun passwordValidation(context: Context, password: String, edPassword: EditText): Boolean {
                val passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@\$*&]).{8,}\$"

                if (!password.matches(passwordRegex.toRegex())) {
                    edPassword.error = "Password must contain at least 8 characters with at least one special character (@$*&), one digit, one uppercase letter, and one lowercase letter."
                    return false
                }
                return true
            }
        }
    }
