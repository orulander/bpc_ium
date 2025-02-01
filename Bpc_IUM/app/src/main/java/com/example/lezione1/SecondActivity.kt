package com.example.lezione1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonLogout = findViewById<Button>(R.id.btnLogout)

        buttonLogout.setOnClickListener{
            logout()
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
                    home()
                    true
                }
                R.id.navigation_search -> {
                    // Azione per Cerca
                    logout()
                    true
                }
                R.id.navigation_profile -> {
                    // Azione per Profilo
                    profile()
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

    fun home(){
        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }

    fun profile(){
        val intent = Intent(this,ProfileCliente::class.java)
        startActivity(intent)
    }
}