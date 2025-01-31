package com.example.lezione1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class RegisterActivityContinuo : AppCompatActivity() {
    object GlobalData {
        var user_list = mutableListOf<User>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.register_continuo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonRegistra = findViewById<Button>(R.id.btnRegister)
        buttonRegistra.isEnabled = false
        buttonRegistra.alpha = 0.3f
        val nameEt = findViewById<EditText>(R.id.etName)
        val cognomeEt = findViewById<EditText>(R.id.etCognome)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val pesoEt = findViewById<EditText>(R.id.etPeso)
        val altezzaEt = findViewById<EditText>(R.id.etAltezza)
        val spinner: Spinner = findViewById(R.id.spinner)

        val adapter_spinner = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )

        buttonRegistra.setOnClickListener {
            var invalid = false
            val peso = pesoEt.text.toString().toDoubleOrNull()
            val altezza = pesoEt.text.toString().toDoubleOrNull()

            if (peso == null || peso <= 0) {
                pesoEt.text.clear()
                pesoEt.setError("Il peso non può zero")
                invalid = true
            }
            if (altezza == null || altezza <= 0) {
                altezzaEt.text.clear()
                altezzaEt.setError("L'altezza non può essere minore di zero")
                invalid = true
            }
            if (!invalid){
                register()
            }
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                updateRegisterButton(nameEt, cognomeEt, radioGroup, pesoEt, altezzaEt, spinner, buttonRegistra)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }

        nameEt.addTextChangedListener(textWatcher)
        cognomeEt.addTextChangedListener(textWatcher)
        altezzaEt.addTextChangedListener(textWatcher)
        pesoEt.addTextChangedListener(textWatcher)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                updateRegisterButton(nameEt, cognomeEt, radioGroup, pesoEt, altezzaEt, spinner, buttonRegistra)
            } else {
                buttonRegistra.alpha = 0.3f
                buttonRegistra.isEnabled = false
            }
        }

        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter_spinner
    }

    fun updateRegisterButton(etName : EditText, etCognome: EditText, radioGroup: RadioGroup, etPeso: EditText, etAltezza: EditText, spinner: Spinner, button: Button){
        if(etName.text.isNotBlank() && etCognome.text.isNotBlank() && radioGroup.checkedRadioButtonId != -1 && etPeso.text.isNotBlank() && etAltezza.text.isNotBlank()){
            button.alpha = 1f
            button.isEnabled = true
        }else{
            button.alpha = 0.3f
            button.isEnabled = false
        }
    }

    fun register(){
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
