package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class PsychViewFeedback : AppCompatActivity() {

    private lateinit var reviewsLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_feedback_page)

        val dismissButton = findViewById<Button>(R.id.button_dismiss)

        reviewsLinearLayout = findViewById(R.id.reviews_linearlayout)

        dismissButton.setOnClickListener {
            val intent = Intent(this, ThankYouActivityPsych::class.java)
            startActivity(intent)
        }



        getReviewsFromFirestore()
    }

    private fun getReviewsFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val className = "Psychology"

        db.collection("Classes_Psych").document(className).collection("Reviews_Psych")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    val reviewTitle = document.getString("Title")
                    val reviewRating = document.getDouble("Rating")
                    val reviewComment = document.getString("Comment")
                    val reviewName = document.getString("Name")

                    val reviewTextView = TextView(this)




                    if (reviewName==null){
                        val nameTitle = ""
                        val reviewName = ""
                        reviewTextView.text = "Title:$reviewTitle $nameTitle$reviewName\nRating: $reviewRating\n$reviewComment\n-----------------------------------------------------------------------------------------\n"
                    }else{
                        val nameTitle ="Name:"
                        reviewTextView.text = "Title:$reviewTitle \n$nameTitle$reviewName\nRating: $reviewRating\n$reviewComment\n-----------------------------------------------------------------------------------------\n"
                    }




                    reviewTextView.textSize = 16f


                    reviewsLinearLayout.addView(reviewTextView)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting reviews: ", exception)
            }
    }

    companion object {
        private const val TAG = "FeedbackPage"
    }
}
