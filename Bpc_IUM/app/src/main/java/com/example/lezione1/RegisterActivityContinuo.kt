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
        val button = findViewById<Button>(R.id.btnRegister)
        button.isEnabled = false
        button.alpha = 0.3f
        val nameEt = findViewById<EditText>(R.id.etName)
        val cognomeEt = findViewById<EditText>(R.id.etCognome)
        val pesoEt = findViewById<EditText>(R.id.etPeso)
        val altezzaEt = findViewById<EditText>(R.id.etAltezza)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val spinner: Spinner = findViewById(R.id.spinner)

        val adapter_spinner = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )



        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //updateRegisterButton(usernameEt, passwordEt, mailEt, date, checkbox, button)
                updateRegisterButton(nameEt, cognomeEt, altezzaEt, pesoEt, radioGroup, spinner, button)
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

        // comportamento radioButton

        // Aggiungi un listener al RadioGroup
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Verifica se è stato selezionato almeno un RadioButton
            if (checkedId != -1) {
                // Un RadioButton è stato selezionato, abilita il pulsante
                updateRegisterButton(nameEt, cognomeEt, altezzaEt, pesoEt, radioGroup, spinner, button)
            } else {
                // Nessun RadioButton è selezionato, disabilita il pulsante
                button.alpha = 0.3f
                button.isEnabled = false
            }
        }

        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter_spinner
    }

    fun register(name: String, cognome: String, altezza:String, peso: String){
        var new_user = User(name ,cognome,altezza, peso)
        GlobalData.user_list.add(new_user)
        Log.i("myTag",GlobalData.user_list.toString())
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("fromRegisterActivity", true)
        startActivity(intent)
    }

    fun updateRegisterButton(etName : EditText, etCognome: EditText, etPeso: EditText, etAltezza: EditText, radioGroup: RadioGroup, spinner: Spinner, button: Button){
        if(etName.text.isNotBlank() && etCognome.text.isNotBlank() && etPeso.text.isNotBlank() && etAltezza.text.isNotBlank() && radioGroup.checkedRadioButtonId != -1){
            button.alpha = 1f
            button.isEnabled = true
        }else{
            button.alpha = 0.3f
            button.isEnabled = false
        }
    }

}