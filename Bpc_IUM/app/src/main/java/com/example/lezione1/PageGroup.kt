package com.example.lezione1
import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class PageGroup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.page_group)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backArrow: ImageView = findViewById(R.id.back_arrow)

        // Click sulla freccia per aprire SecondActivity
        backArrow.setOnClickListener {
            home()
        }

        val chatTextView: TextView = findViewById(R.id.toolbar_chat)
        val groupsTextView: TextView = findViewById(R.id.toolbar_groups)

        // Click su "Chat" → Vai a ChatActivity
        chatTextView.setOnClickListener {
            chat()
        }

        // Click su "Gruppi" → Vai a GroupsActivity
        groupsTextView.setOnClickListener {
            gruppi()
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

    fun chat() {
        val intent = Intent(this, PageChat::class.java)
        startActivity(intent)
    }

    fun gruppi() {
        val intent = Intent(this, PageGroup::class.java)
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