package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FeedbackPage : AppCompatActivity() {
        private lateinit var reviewTitleEditText: TextView
        private lateinit var reviewRatingBar: TextView
        private lateinit var reviewEditText: TextView
        private lateinit var backButton: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.view_feedback_page)

            val db = Firebase.firestore

            // Initialize views
            reviewTitleEditText = findViewById(R.id.review_title_textview)
            reviewRatingBar = findViewById(R.id.review_rating_textview)
            reviewEditText = findViewById(R.id.review_comment_textview)
            backButton = findViewById(R.id.back_button)

            // Get student ID and class name
            val studentID = "002551307"
            val className = "Psychology"

            // Retrieve data from Firestore
            db.collection("Classes").document(className).collection(className + "Review").document(studentID)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        // Get data from Firestore document
                        val reviewTitle = document.getString("Title")
                        val reviewRating = document.getString("Rating")
                        val reviewComment = document.getString("Comment")

                        // Set data to text views
                        reviewTitleEditText.text = reviewTitle
                        reviewRatingBar.text = reviewRating
                        reviewEditText.text = reviewComment
                    } else {
                        // Handle the case where the document does not exist
                        // ...
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle any errors
                    // ...
                }

            backButton.setOnClickListener {
                val intent = Intent(this, ThankYouActivity::class.java)
                startActivity(intent)
            }
        }
    }
