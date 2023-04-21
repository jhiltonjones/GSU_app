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
        val viewFeedbackButton = findViewById<Button>(R.id.btn_view_feedbackc)

        homeButton.setOnClickListener {
            val intent = Intent(this, ClassList::class.java)
            startActivity(intent)
        }
        viewFeedbackButton.setOnClickListener {
            val intent = Intent(this, ClassList::class.java)
            val passedName = intent.getStringExtra("classname");
            intent.putExtra("classname",passedName);
            startActivity(intent)
        }
    }
}
