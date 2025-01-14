package com.example.a1th_lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a1th_lab.ui.theme._1th_labTheme
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val squareButton = findViewById<Button>(R.id.btn_square)
        val rectangleButton = findViewById<Button>(R.id.btn_rectangle)
        val circleButton = findViewById<Button>(R.id.btn_circle)
        val socialMediaButton = findViewById<Button>(R.id.social_media)

        squareButton.setOnClickListener {
            startActivity(Intent(this, SquareActivity::class.java))
        }
        rectangleButton.setOnClickListener {
            startActivity(Intent(this, RectangleActivity::class.java))
        }
        circleButton.setOnClickListener {
            startActivity(Intent(this, CircleActivity::class.java))
        }
        socialMediaButton.setOnClickListener {
            startActivity(Intent(this, SocialActivity::class.java))
        }
    }
}