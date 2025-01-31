package com.example.lezione1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class RegisterActivity : AppCompatActivity() {
    object GlobalData{
        var user_list = mutableListOf<User>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonContinua = findViewById<Button>(R.id.btnContinua)
        buttonContinua.isEnabled = false
        buttonContinua.alpha = 0.3f
        val usernameEt = findViewById<EditText>(R.id.etUsername)
        val mailEt = findViewById<EditText>(R.id.etMail)
        val passwordEt = findViewById<EditText>(R.id.etPassword)
        val confirmPasswordEt = findViewById<EditText>(R.id.etConfirmPassword)
        val date = findViewById<EditText>(R.id.etDate)
        val tvDate = findViewById<TextView>(R.id.tvDate)
        date.setOnClickListener {
            datePicker(date)
        }

        buttonContinua.setOnClickListener {
            var invalid = false

            if (RegisterActivity.GlobalData.user_list.any{it.name == usernameEt.text.toString()}){
                usernameEt.setError("Username già esistente")
                invalid = true
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(mailEt.text.toString()).matches()){
                mailEt.setError("E-mail non valida")
                invalid = true
            }
            if (passwordEt.text.length < 8){
                passwordEt.text.clear()
                confirmPasswordEt.text.clear()
                passwordEt.setError("La password deve avere almeno 8 caratteri")
                invalid = true
            }
            if (passwordEt.text.toString() != confirmPasswordEt.text.toString()) {
                passwordEt.text.clear()
                confirmPasswordEt.text.clear()
                passwordEt.setError("Le password non corrispondono")
                confirmPasswordEt.setError("Le password non corrispondono")
                invalid = true
            }
            if (!invalid){
                continua(
                    usernameEt.text.toString(),
                    mailEt.text.toString(),
                    passwordEt.text.toString(),
                    date.text.toString()
                )
            }
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                updateContinuaButton(usernameEt, mailEt, passwordEt, date, buttonContinua)

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }

        usernameEt.addTextChangedListener(textWatcher)
        mailEt.addTextChangedListener(textWatcher)
        passwordEt.addTextChangedListener(textWatcher)
        date.addTextChangedListener(textWatcher)

        val help = findViewById<TextView>(R.id.tvHelp)
        TooltipCompat.setTooltipText(help, "La password deve avere almeno 8 caratteri")
    }

    fun datePicker(date: EditText){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                date.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    fun updateContinuaButton(etUsername : EditText, etMail: EditText, etPassword: EditText, etDate: EditText, button: Button){
        if(etUsername.text.isNotBlank() && etMail.text.isNotBlank() && etPassword.text.isNotBlank() && etDate.text.isNotBlank()){
            button.alpha = 1f
            button.isEnabled = true
        }else{
            button.alpha = 0.3f
            button.isEnabled = false
        }
    }

    fun continua(username: String, mail: String,password:String,date: String) {
        var new_user = User(username,mail,password, date)
        GlobalData.user_list.add(new_user)
        Log.i("myTag", GlobalData.user_list.toString())

        val intent = Intent(this, RegisterActivityContinuo::class.java)
        startActivity(intent)
    }
}
