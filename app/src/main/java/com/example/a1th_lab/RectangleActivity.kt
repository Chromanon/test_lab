package com.example.a1th_lab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RectangleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rectangle)

        val lengthInput = findViewById<EditText>(R.id.input_length)
        val widthInput = findViewById<EditText>(R.id.input_width)
        val calculateButton = findViewById<Button>(R.id.btn_calculate)
        val resultText = findViewById<TextView>(R.id.result_text)

        calculateButton.setOnClickListener {
            val length = lengthInput.text.toString().toDoubleOrNull()
            val width = widthInput.text.toString().toDoubleOrNull()

            if (length != null && width != null) {
                val area = length * width
                resultText.text = "Area: $area"
            } else {
                resultText.text = "Invalid input"
            }
        }
    }
}
