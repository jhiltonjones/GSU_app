package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThankYouActivityPsych : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.submit_feedback_confirmation_psych)

        val writeReviewButton = findViewById<Button>(R.id.btn_home_menup)
        val viewFeedbackButton = findViewById<Button>(R.id.btn_view_feedbackp)

        writeReviewButton.setOnClickListener {
            val intent = Intent(this, ClassList::class.java)
            startActivity(intent)
        }
        viewFeedbackButton.setOnClickListener {
            val intent = Intent(this, PsychViewFeedback::class.java)
            startActivity(intent)
        }
    }
}
