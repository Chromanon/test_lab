package com.example.a1th_lab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.PI

class CircleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle)

        val radiusInput = findViewById<EditText>(R.id.input_radius)
        val calculateButton = findViewById<Button>(R.id.btn_calculate)
        val resultText = findViewById<TextView>(R.id.result_text)

        calculateButton.setOnClickListener {
            val radius = radiusInput.text.toString().toDoubleOrNull()
            if (radius != null) {
                val area = PI * radius * radius
                resultText.text = "Area: $area"
            } else {
                resultText.text = "Invalid input"
            }
        }
    }
}
