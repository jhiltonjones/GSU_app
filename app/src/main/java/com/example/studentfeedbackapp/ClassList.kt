package com.example.studentfeedbackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

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

        calculusButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        psychologyButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        philosophyButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        computerScienceButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        physicalEducationButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        logoutButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
