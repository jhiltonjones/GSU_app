package com.example.studentfeedbackapp

import android.content.Intent
import android.media.Rating
import android.os.Bundle
import android.widget.*
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

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

            // Save data to Firebase
            saveFireStore(reviewComment)

            // Pass data to FeedbackPage activity
            val intent = Intent(this, FeedbackPage::class.java).apply {
                putExtra("reviewTitle", reviewTitle)
                putExtra("reviewRating", reviewRating)
                putExtra("reviewComment", reviewComment)
            }
            startActivity(intent)
        }


    }
    fun saveFireStore(commentsText:String){

        val studentID ="002551307"
        val reviewTitle = reviewTitleEditText.text.toString()
        val reviewRating = reviewRatingBar.rating.toString()
        val reviewComment = reviewEditText.text.toString()

        val db = FirebaseFirestore.getInstance()
        val map: HashMap<String, String> = hashMapOf(
            "Title" to reviewTitle,
            "Rating" to reviewRating,
            "Comment" to reviewComment,
            "StudentID" to studentID
        )

        val className = "Psychology"

        db.collection("Classes").document(className).collection(className+"Review").document(studentID)
            .set(map)
            .addOnSuccessListener {
                Toast.makeText(this@FeedbackActivity,"record added Successfully",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@FeedbackActivity,"record Failed to add",Toast.LENGTH_SHORT).show()
            }

    }
}
