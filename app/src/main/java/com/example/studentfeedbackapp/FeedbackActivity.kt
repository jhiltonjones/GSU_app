package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val button = findViewById<Button>(R.id.submit)
        val comments = findViewById<EditText>(R.id.comments)
        val answer1 = findViewById<RadioGroup>(R.id.answer1)
        val answer2 = findViewById<RadioGroup>(R.id.answer2)
        val answer3 = findViewById<RadioGroup>(R.id.answer3)

        button.setOnClickListener {
            val selected1 = answer1.findViewById<RadioButton>(answer1.checkedRadioButtonId)
            val selected2 = answer2.findViewById<RadioButton>(answer2.checkedRadioButtonId)
            val selected3 = answer3.findViewById<RadioButton>(answer3.checkedRadioButtonId)
            val commentsText = comments.text.toString()
            val message = "1. ${selected1?.text}\n2. ${selected2?.text}\n3. ${selected3?.text}\nComments: $commentsText"

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ThankYouActivity::class.java)
            startActivity(intent)
        }
    }
}
