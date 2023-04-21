package com.example.studentfeedbackapp

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Rating
import android.os.Bundle
import android.widget.*
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class FeedbackActivity : AppCompatActivity() {

    private lateinit var reviewTitleEditText: EditText
    private lateinit var reviewRatingBar: RatingBar
    private lateinit var reviewEditText: EditText
    private lateinit var reviewNameText: EditText
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        reviewTitleEditText = findViewById(R.id.title_edittext)
        reviewRatingBar = findViewById(R.id.ratingbar)
        reviewEditText = findViewById(R.id.review_edittext)
        reviewNameText = findViewById(R.id.NameText)
        submitButton = findViewById(R.id.submit)

        submitButton.setOnClickListener {

            val reviewTitle = reviewTitleEditText.text.toString()
            val reviewName = reviewNameText.text.toString()
            val reviewRating = reviewRatingBar.rating
            val reviewComment = reviewEditText.text.toString()



            saveFirestore( reviewTitle, reviewRating, reviewComment,reviewName)

            val passedName = intent.getStringExtra("classname");
            val intent = Intent(this, CalcViewFeedback::class.java).apply {
                putExtra("reviewTitle", reviewTitle)
                putExtra("reviewName", reviewName)
                putExtra("reviewRating", reviewRating)
                putExtra("reviewComment", reviewComment)
                putExtra("classname", passedName);
            }
            startActivity(intent)

            //val intent = Intent(this, CalcViewFeedback::class.java);
            //val passedName = intent.getStringExtra("classname");
            //intent.putExtra("classname", passedName);
           // startActivity(intent);

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
        var className="";
        val passedName = intent.getStringExtra("classname");
        if(passedName==null){
            className ="void";
        }else {
            className = passedName;
        }
        db.collection("Classes").document(className)
            .collection("Reviews").document()
            .set(reviewMap)
            .addOnSuccessListener {
                Toast.makeText(
                    this@FeedbackActivity,
                    "Record added successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    this@FeedbackActivity,
                    "Failed to add record",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
