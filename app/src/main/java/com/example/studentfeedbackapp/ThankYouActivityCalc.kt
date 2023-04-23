package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThankYouActivityCalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.submit_feedback_confirmation_calc)

        val homeButton = findViewById<Button>(R.id.btn_home_menuc)


        homeButton.setOnClickListener {
            val intent = Intent(this, ClassList::class.java)
            startActivity(intent)
        }

    }
}
