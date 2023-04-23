package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        // Initialize Firebase Authentication instance
        auth = FirebaseAuth.getInstance()

        val registerButton = findViewById<Button>(R.id.register_button)
        val emailEditText = findViewById<EditText>(R.id.register_email)
        val passwordEditText = findViewById<EditText>(R.id.register_password)
        


        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()


            // Call the Firebase Authentication API to create a new user with the specified email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // User registration successful, navigate to the ClassList activity
                        val intent = Intent(this, ClassList::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // User registration failed, display an error message
                        Toast.makeText(
                            this,
                            "Registration failed: " + task.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
