package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Get the current user email from the intent
        val currentUser = intent.getStringExtra("useremail") ?: ""
        println("Received:"+currentUser)


        getUserInfoFromFirestore(currentUser)
    }

    private fun getUserInfoFromFirestore(currentUser:String) {
        val db = FirebaseFirestore.getInstance()

        db.collection("Students")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if(currentUser == document.getString("Email")) {
                        val age = document.getString("Age")
                        val name = document.getString("Name")
                        val id = document.getString("StudentID")
                        val email = document.getString("Email")
                        // TODO: Do something with the retrieved user info
                        println("this runs")
                        println(age)
                        println(name)
                        println(id)
                        println(email)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting reviews: ", exception)
            }
    }

    companion object {
        private const val TAG = "Profile"
    }
}



