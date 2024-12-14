package com.example.a1th_lab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SquareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_square)

        val sideInput = findViewById<EditText>(R.id.input_side)
        val calculateButton = findViewById<Button>(R.id.btn_calculate)
        val resultText = findViewById<TextView>(R.id.result_text)

        calculateButton.setOnClickListener {
            val side = sideInput.text.toString().toDoubleOrNull()
            if (side != null) {
                val area = side * side
                resultText.text = "Area: $area"
            } else {
                resultText.text = "Invalid input"
            }
        }
    }
}
