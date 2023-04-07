package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    private lateinit var reviewTitleEditText: EditText
    private lateinit var reviewRatingBar: RatingBar
    private lateinit var reviewEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        reviewTitleEditText = findViewById(R.id.title_edittext)
        reviewRatingBar = findViewById(R.id.ratingbar)
        reviewEditText = findViewById(R.id.review_edittext)
        submitButton = findViewById(R.id.submit)

        submitButton.setOnClickListener {
            val reviewTitle = reviewTitleEditText.text.toString()
            val reviewRating = reviewRatingBar.rating
            val reviewComment = reviewEditText.text.toString()



            val intent = Intent(this, ThankYouActivity::class.java)
            startActivity(intent)
        }
    }
}
