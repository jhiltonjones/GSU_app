package com.example.studentfeedbackapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

class ClassList : AppCompatActivity() {

    private lateinit var classList: MutableList<String>
    private lateinit var classListtest: MutableList<String>
    private lateinit var searchView: SearchView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_list)
        searchView = findViewById(R.id.search_view)
        val UserEmail = intent.getStringExtra("useremail")

        // Create the list of classes and adapter
        classList = mutableListOf(
            "Calculus",
            "Psychology",
            "Philosophy",
            "Computer Science",
            "Physical Education",
            "Music",
            "Theatre",
            "Physics",
            "Chemistry",
            "Biology",
            "English Literature",
            "English Language",
            "Geography",
            "Politics",
            "History"
        )
        classListtest = mutableListOf(
            "Calculus",
            "Psychology",
            "Philosophy",
            "Computer Science",
            "Physical Education",
            "Music",
            "Theatre",
            "Physics",
            "Chemistry",
            "Biology",
            "English Literature",
            "English Language",
            "Geography",
            "Politics",
            "History"
        )
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, classList)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    adapter.clear()
                    adapter.addAll(classListtest)


                } else {
                    val filteredClassNames = classList.filter { it.contains(newText, true) }
                    adapter.clear()
                    adapter.addAll(filteredClassNames)

                }
                adapter.notifyDataSetChanged()
                return true
            }
        })

        // Set the adapter to the ListView
        val listView: ListView = findViewById(R.id.list_view)
        listView.adapter = adapter

        // Set the click listener for each item in the ListView
        val intent = Intent(this, MainActivity::class.java)
        listView.setOnItemClickListener { _, _, position, _ ->
            val className = classList[position]
            intent.putExtra("classname", className)
            startActivity(intent)
            println("className:" + className)
            println("test")
        }

        // Set the click listener for the profile button
        val ProfileButton: Button = findViewById(R.id.profile)
        ProfileButton.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            intent.putExtra("useremail",UserEmail)
            startActivity(intent)
        }
    }



    private fun updateClassButtons(classNames: List<String>) {
        val calculusButton = findViewById<Button>(R.id.btn_calculus)
        val psychologyButton = findViewById<Button>(R.id.btn_psychology)
        val philosophyButton = findViewById<Button>(R.id.btn_philosophy)
        val computerScienceButton = findViewById<Button>(R.id.btn_computer_science)
        val physicalEducationButton = findViewById<Button>(R.id.btn_physical_education)

        val buttons = mapOf(
            "Calculus" to calculusButton,
            "Psychology" to psychologyButton,
            "Philosophy" to philosophyButton,
            "Computer Science" to computerScienceButton,
            "Physical Education" to physicalEducationButton


        )

        for ((className, button) in buttons) {
            if (classNames.contains(className)) {
                button.visibility = View.VISIBLE
                button.setOnClickListener {
                    startFeedbackActivity(className)
                }
            } else {
                button.visibility = View.GONE
            }
        }
    }


    private fun startFeedbackActivity(className: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("classname", className)
        }
        startActivity(intent)
    }
}


