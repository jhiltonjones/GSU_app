package com.example.studentfeedbackapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class ReviewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val submit_button = findViewById<Button>(R.id.submit_button)

        submit_button.setOnClickListener {
            // Code to run when the button is clicked
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }
    }
}
