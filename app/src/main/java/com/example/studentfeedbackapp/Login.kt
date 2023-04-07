package com.example.studentfeedbackapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val submitButton = findViewById<Button>(R.id.btn_login)

        // Set up click listener for the submit button
        submitButton.setOnClickListener {
            // Launch the ClassList activity
            val intent = Intent(this, ClassList::class.java)
            startActivity(intent)
        }
    }
}