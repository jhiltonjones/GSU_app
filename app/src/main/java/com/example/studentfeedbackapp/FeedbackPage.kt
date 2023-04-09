package com.example.studentfeedbackapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FeedbackPage : AppCompatActivity() {

    private lateinit var reviewsLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_feedback_page)

        val dismissButton = findViewById<Button>(R.id.button_dismiss)

        reviewsLinearLayout = findViewById(R.id.reviews_linearlayout)

        dismissButton.setOnClickListener {
            val intent = Intent(this, ThankYouActivity::class.java)
            startActivity(intent)
        }



        getReviewsFromFirestore()
    }

    private fun getReviewsFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val className = "Psychology"

        db.collection("Classes").document(className).collection("Reviews")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val reviewTitle = document.getString("Title")
                    val reviewRating = document.getDouble("Rating")
                    val reviewComment = document.getString("Comment")

                    // Create a new TextView to display the review
                    val reviewTextView = TextView(this)
                    reviewTextView.text = "$reviewTitle\nRating: $reviewRating\n$reviewComment"
                    reviewTextView.textSize = 16f

                    // Add the TextView to the LinearLayout
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



