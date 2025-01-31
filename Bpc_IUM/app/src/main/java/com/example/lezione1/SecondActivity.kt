package com.example.lezione1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {
    var score = 0
    var current_vote = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val seekbar = findViewById<SeekBar>(R.id.seekbar)
        val vote = findViewById<TextView>(R.id.tvVote)
        val logoutButton = findViewById<Button>(R.id.btnLogout)

        logoutButton.setOnClickListener{
            logout()
        }

        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                vote.text = "${progress-5}"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        val taskText = findViewById<EditText>(R.id.etTask)
        val buttonAddTask = findViewById<Button>(R.id.btnAddTask)
        val buttonClearTasks = findViewById<Button>(R.id.btnClearTasks)
        val buttonCompleteAll = findViewById<ImageButton>(R.id.ibTask)
        val taskList = mutableListOf<Task>()
        val recyclerView = findViewById<RecyclerView>(R.id.rvTasks)
        val taskCount = findViewById<TextView>(R.id.tvCount)
        val adapter = TaskAdapter(taskList,taskCount)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonAddTask.setOnClickListener {
            val task = taskText.text.toString()
            taskText.text.clear()
            taskList.add(Task(task))
            adapter.notifyItemInserted(taskList.size - 1)
        }

        buttonClearTasks.setOnClickListener {
            taskList.removeAll { it.isCompleted }
            adapter.notifyDataSetChanged()
        }

        buttonCompleteAll.setOnClickListener{
            if(taskList.all{it.isCompleted}){
                taskList.forEach{it.isCompleted = false}
            }else {
                taskList.forEach { it.isCompleted = true }
            }
            adapter.notifyDataSetChanged()
        }

        /**
         * dovrebbe far funzionare la navbar, non funziona perché ho collegato tutto con logout
         * per fare un check veloce e perché non abbiamo le pagine necessarie
         **/
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navbar)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Azione per Home
                    logout()
                    true
                }
                R.id.navigation_search -> {
                    // Azione per Cerca
                    logout()
                    true
                }
                R.id.navigation_profile -> {
                    // Azione per Profilo
                    logout()
                    true
                }
                else -> false
            }
        }


        /**
         * dovrebbe far funzionare la toolbar, non so se funziona perché non riesco a raggiungere
         * la second activity
         **/
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Nasconde il titolo predefinito

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.aaaa -> {
                    logout()
                    true
                }
                R.id.modifica -> {
                    logout()
                    true
                }
                R.id.logout -> {
                    logout()
                    true
                }
                else -> false
            }
        }
    }

    fun logout(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}