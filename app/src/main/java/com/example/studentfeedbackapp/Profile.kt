package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {


    lateinit var User_name: TextView
    lateinit var User_Age: TextView
    lateinit var User_major: TextView
    lateinit var User_ID: TextView
    lateinit var User_grad: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        User_name = findViewById(R.id.user_name)
        User_Age = findViewById(R.id.age_text)
        User_major = findViewById(R.id.major)
        User_ID = findViewById(R.id.Student_id)
        User_grad = findViewById(R.id.graduating)

        // Get the current user email from the intent
        val currentUser = intent.getStringExtra("useremail") ?: ""
        println("Received:"+currentUser)


        getUserInfoFromFirestore(currentUser)
        val LogoutButton: Button = findViewById(R.id.logout_button)
        LogoutButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
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
                        val major = document.getString("Major")
                        val Graduate_year = document.getString("Graduating_Year")
                        // TODO: Do something with the retrieved user info
                        User_name.setText("Welcome $name!\n")
                        User_Age.setText("\nAge:$age")
                        User_major.setText("Major:$major")
                        User_ID.setText("Student ID: $id")
                        User_grad.setText("Graduating year: $Graduate_year")
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



