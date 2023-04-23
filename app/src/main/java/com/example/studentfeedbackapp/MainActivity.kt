package com.example.studentfeedbackapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_page)

        val writeReviewButton = findViewById<Button>(R.id.btn_write_review)

        writeReviewButton.setOnClickListener {
            val intent = Intent(this, FeedbackActivity::class.java)
            val passedname= intent.getStringExtra("classname")
            intent.putExtra("classname",passedname)
            startActivity(intent)
        }
        val viewReview = findViewById<Button>(R.id.btn_view_review)

        viewReview.setOnClickListener {
            val intent = Intent(this, FeedbackPage::class.java)
            val passedName= intent.getStringExtra("classname")
            intent.putExtra("classname",passedName)
            println("name:"+passedName)
            startActivity(intent)
        }
    }
}
