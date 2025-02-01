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

class ProfileCliente : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.profile_cliente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val menuIcon: ImageView = findViewById(R.id.menu_icon)

        // Quando si clicca sull'icona del menu, mostra il popup
        menuIcon.setOnClickListener { view ->
            showPopupMenu(view)
        }

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
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}