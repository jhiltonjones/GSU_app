package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentfeedbackapp.R.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class CalculusFeedbackPage: AppCompatActivity() {

        private lateinit var reviewTitleEditTextc: EditText
        private lateinit var reviewRatingBarc: RatingBar
        private lateinit var reviewEditTextc: EditText
        private lateinit var reviewNameTextc: EditText
        private lateinit var submitButtonc: Button


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(layout.calc_feedback_input)

            reviewTitleEditTextc = findViewById(id.title_edittextc)
            reviewRatingBarc = findViewById(id.ratingbarc)
            reviewEditTextc = findViewById(id.review_edittextc)
            reviewNameTextc = findViewById(id.NameTextc)
            submitButtonc = findViewById(id.submitc)

            submitButtonc.setOnClickListener {

                val reviewTitle = reviewTitleEditTextc.text.toString()
                val reviewName = reviewNameTextc.text.toString()
                val reviewRating = reviewRatingBarc.rating
                val reviewComment = reviewEditTextc.text.toString()



                saveFirestore( reviewTitle, reviewRating, reviewComment,reviewName)


                val intent = Intent(this, CalcViewFeedback::class.java).apply {
                    putExtra("reviewTitle", reviewTitle)
                    putExtra("reviewName", reviewName)
                    putExtra("reviewRating", reviewRating)
                    putExtra("reviewComment", reviewComment)
                }
                startActivity(intent)
            }
        }

        private fun saveFirestore(reviewTitle: String, reviewRating: Float, reviewComment: String,reviewName: String) {
            val studentID ="002551307"
            val db = FirebaseFirestore.getInstance()

            val reviewMap: HashMap<String, Any> = hashMapOf(
                "Title" to reviewTitle,
                "Name" to reviewName,
                "Rating" to reviewRating,
                "Comment" to reviewComment,
                "StudentID" to studentID,
                "Timestamp" to FieldValue.serverTimestamp()
            )

            val className = "Calculus"

            db.collection("Classes_Calc").document(className)
                .collection("Reviews_Calc").document()
                .set(reviewMap)
                .addOnSuccessListener {
                    Toast.makeText(
                        this@CalculusFeedbackPage,
                        "Record added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this@CalculusFeedbackPage,
                        "Failed to add record",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

