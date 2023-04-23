package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        // Initialize Firebase Authentication instance
        auth = FirebaseAuth.getInstance()

        val registerButton = findViewById<Button>(R.id.register_button)
        val nameEditText = findViewById<EditText>(R.id.register_name)
        val idEditText = findViewById<EditText>(R.id.register_id)
        val emailEditText = findViewById<EditText>(R.id.register_email)
        val passwordEditText = findViewById<EditText>(R.id.register_password)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Call the Firebase Authentication API to create a new user with the specified email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // User registration successful, update the user profile with the name and id fields
                        val user = auth.currentUser
                        val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()

                        user?.updateProfile(profileUpdates)?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                // User profile update successful, navigate to the ClassList activity
                                val intent = Intent(this, ClassList::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // User profile update failed, display an error message
                                Toast.makeText(
                                    this,
                                    "User profile update failed: " + it.exception?.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        // Store the name and id fields in the Firebase Realtime Database
                        val database = Firebase.database.reference
                        val userId = user?.uid

                        if (userId != null) {
                            database.child("users").child(userId).child("name").setValue(name)
                            database.child("users").child(userId).child("id").setValue(id)
                        }
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

