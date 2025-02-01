package com.example.lezione1
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class PageSearch1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.page_search1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val menuIcon: ImageView = findViewById(R.id.menu_icon)

        menuIcon.setOnClickListener { view ->
            showPopupMenu(view)
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
                    search()
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

    fun search(){
        val intent = Intent(this,PageSearch1::class.java)
        startActivity(intent)
    }

    fun profile(){
        val intent = Intent(this,ProfileCliente::class.java)
        startActivity(intent)
    }

    fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.toolbar_menu, popup.menu)

        // Gestire il click sugli elementi del menu
        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.aaaa -> {
                    true
                }
                R.id.modifica -> {
                    true
                }
                R.id.logout -> {
                    logout()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}