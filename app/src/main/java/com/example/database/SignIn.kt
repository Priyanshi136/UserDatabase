package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.database.databinding.ActivitySignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    lateinit var databaseReference : DatabaseReference

    companion object{
        const val KEY1 = "com.example.database.SignInActivity.name"
        const val KEY2 = "com.example.database.SignInActivity.email"
        const val KEY3 = "com.example.database.SignInActivity.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSignIn.setOnClickListener {

            val uniqueId = binding.userNameEditText.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            }else{
                Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun readData(uniqueId: String) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if(it.exists()){
                val email = it.child("email").value.toString()
                val name = it.child("name").value.toString()
                val userId = it.child("uniqueId").value.toString()
                val intent = Intent(this, Welcome::class.java)
                intent.putExtra(KEY1, name)
                intent.putExtra(KEY2, email)
                intent.putExtra(KEY3, userId)
                startActivity(intent)
            }else{
                Toast.makeText(this, "User doesn't exist, please first sign up", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}