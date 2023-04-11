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

class PsychFeedbackPage: AppCompatActivity() {

        private lateinit var reviewTitleEditTextp: EditText
        private lateinit var reviewRatingBarp: RatingBar
        private lateinit var reviewEditTextp: EditText
        private lateinit var reviewNameTextp: EditText
        private lateinit var submitButtonp: Button


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(layout.psych_feedback_input)

            reviewTitleEditTextp = findViewById(id.title_edittextp)
            reviewRatingBarp = findViewById(id.ratingbarp)
            reviewEditTextp = findViewById(id.review_edittextp)
            reviewNameTextp = findViewById(id.NameTextp)
            submitButtonp = findViewById(id.submitp)

            submitButtonp.setOnClickListener {

                val reviewTitle = reviewTitleEditTextp.text.toString()
                val reviewName = reviewNameTextp.text.toString()
                val reviewRating = reviewRatingBarp.rating
                val reviewComment = reviewEditTextp.text.toString()



                saveFirestore( reviewTitle, reviewRating, reviewComment,reviewName)


                val intent = Intent(this, PsychViewFeedback::class.java).apply {
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

            val className = "Psychology"

            db.collection("Classes_Psych").document(className)
                .collection("Reviews_Psych").document()
                .set(reviewMap)
                .addOnSuccessListener {
                    Toast.makeText(
                        this@PsychFeedbackPage,
                        "Record added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this@PsychFeedbackPage,
                        "Failed to add record",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

