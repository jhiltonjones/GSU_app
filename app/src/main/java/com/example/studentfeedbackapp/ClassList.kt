package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ClassList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_list)

        val calculusButton = findViewById<Button>(R.id.btn_calculus)
        val psychologyButton = findViewById<Button>(R.id.btn_psychology)
        val philosophyButton = findViewById<Button>(R.id.btn_philosophy)
        val computerScienceButton = findViewById<Button>(R.id.btn_computer_science)
        val physicalEducationButton = findViewById<Button>(R.id.btn_physical_education)
        val logoutButton = findViewById<Button>(R.id.btn_log_out)
        val intent = Intent(this, FeedbackActivity::class.java);
        calculusButton.setOnClickListener {
           // val intent = Intent(this, CalculusFeedbackPage::class.java)
            intent.putExtra("classname", "Calculus");
            startActivity(intent)
        }

        psychologyButton.setOnClickListener {
           // val intent = Intent(this, PsychFeedbackPage::class.java)
            intent.putExtra("classname", "Psychology");
            startActivity(intent)
        }

        philosophyButton.setOnClickListener {
            intent.putExtra("classname", "Philosophy");
            startActivity(intent)
        }

        computerScienceButton.setOnClickListener {

            intent.putExtra("classname", "Computer_Science");
            startActivity(intent)
        }

        physicalEducationButton.setOnClickListener {

            intent.putExtra("classname", "Physical_Education");
            startActivity(intent)
        }
        logoutButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun startFeedbackActivity(className: String) {
        val intent = Intent(this, FeedbackActivity::class.java).apply {
            putExtra("class", className)
        }
        startActivity(intent)
    }
}
