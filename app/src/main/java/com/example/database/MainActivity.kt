package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.database.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnSignUp.setOnClickListener {

            val name = binding.etName.text.toString()
            val mail = binding.etMail.text.toString()
            val uniqueId = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")

            val user = User(name, mail, password, uniqueId)
            database.child(uniqueId).setValue(user).addOnSuccessListener {
                binding.etName.text?.clear()
                binding.etMail.text?.clear()
                binding.etPassword.text?.clear()
                binding.etUserName.text?.clear()
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvSignIN.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

    }
}