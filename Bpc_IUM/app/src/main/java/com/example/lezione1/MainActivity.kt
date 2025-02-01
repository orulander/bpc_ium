package com.example.lezione1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fromRegisterActivity = intent.getBooleanExtra("fromRegisterActivity", false)
        if (fromRegisterActivity){
            Toast.makeText(this, getString(R.string.success_register), Toast.LENGTH_SHORT).show()
        }
        RegisterActivity.GlobalData.user_list.add(User("betty","betty@mail.it","12345678", "12/12/1999"))
        RegisterActivity.GlobalData.user_list.add(User("ele","ele@mail.it","12345678", "12/12/1999"))

        val buttonRegister = findViewById<TextView>(R.id.tvRegister)
        buttonRegister.setOnClickListener{
            register()
        }

        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        buttonLogin.alpha = 0.3f
        buttonLogin.isEnabled = false

        buttonLogin.setOnClickListener{
            login()
        }

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(etUsername.text.isNotBlank() && etPassword.text.isNotBlank()){
                    buttonLogin.alpha = 1f
                    buttonLogin.isEnabled = true
                }else{
                    buttonLogin.alpha = 0.3f
                    buttonLogin.isEnabled = false
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }

        etUsername.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
    }

    fun register(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun login(){
        val usernameInput = findViewById<EditText>(R.id.etUsername)
        val passwordInput = findViewById<EditText>(R.id.etPassword)

        val intent = Intent(this, SecondActivity::class.java)

        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()

        val userPresent = RegisterActivity.GlobalData.user_list.any {
            it.name == username && it.password == password
        }

        if(userPresent){
            startActivity(intent)
        }
        else{
            val errorLoginText = findViewById<TextView>(R.id.errorLoginText)
            errorLoginText.visibility = TextView.VISIBLE
            usernameInput.backgroundTintList = ContextCompat.getColorStateList(this, R.color.red)
            passwordInput.backgroundTintList = ContextCompat.getColorStateList(this, R.color.red)
            usernameInput.text.clear()
            passwordInput.text.clear()
        }
    }
}