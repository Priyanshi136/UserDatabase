package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(SignIn.KEY1)
        val email = intent.getStringExtra(SignIn.KEY2)
        val userId = intent.getStringExtra(SignIn.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tVWelcome)
        welcomeText.text = "Welcome $name"

        val mailText = findViewById<TextView>(R.id.tvMail)
        mailText.text = "Mail : $email"

        val idText = findViewById<TextView>(R.id.tvUnique)
        idText.text = "UserId : $userId"
    }
}