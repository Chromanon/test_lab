package com.example.a1th_lab

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException

class SocialActivity : AppCompatActivity() {
    private lateinit var userNameEditText: EditText
    private lateinit var editButton: Button
    private lateinit var saveButton: Button
    private lateinit var selectPhotoButton: Button
    private lateinit var largePhotoImageView: ImageView
    private lateinit var overlay: View

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
        private const val READ_EXTERNAL_STORAGE_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)

        userNameEditText = findViewById(R.id.userName)
        editButton = findViewById(R.id.editButton)
        saveButton = findViewById(R.id.saveButton)
        selectPhotoButton = findViewById(R.id.selectPhotoButton)
        largePhotoImageView = findViewById(R.id.largePhoto)
        overlay = findViewById(R.id.overlay)

        // Показати діалог авторизації при запуску активності
        showLoginDialog()

        editButton.setOnClickListener {
            userNameEditText.isFocusableInTouchMode = true
            userNameEditText.requestFocus()
            userNameEditText.setSelection(userNameEditText.text.length) // Встановлюємо курсор в кінець тексту
            editButton.visibility = View.GONE // Приховуємо кнопку "Edit"
            saveButton.visibility = View.VISIBLE // Показуємо кнопку "Save"
        }

        saveButton.setOnClickListener {
            userNameEditText.isFocusable = false
            userNameEditText.clearFocus()
            editButton.visibility = View.VISIBLE // Показуємо кнопку "Edit"
            saveButton.visibility = View.GONE // Приховуємо кнопку "Save"
        }

        selectPhotoButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_EXTERNAL_STORAGE_REQUEST_CODE)
            } else {
                openGallery()
            }
        }
    }

    private fun showLoginDialog() {
        overlay.visibility = View.VISIBLE // Показуємо прозорий фон

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Login")

        val view = layoutInflater.inflate(R.layout.dialog_login, null)
        builder.setView(view)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)

        // Заповнюємо поля за замовчуванням
        usernameEditText.setText("admin")
        passwordEditText.setText("admin")

        builder.setPositiveButton("Login") { dialog, _ ->
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "admin" && password == "admin") {
                // Успішна авторизація
                // Завантажуємо основний контент активності
                overlay.visibility = View.GONE // Приховуємо прозорий фон
            } else {
                // Неправильний логін або пароль
                // Показати діалог знову, якщо авторизація невдала
                showLoginDialog()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
            finish()
        }

        builder.setOnCancelListener {
            finish() // Закриваємо активність, якщо користувач скасовує авторизацію
        }

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false) // Забороняємо закриття діалогу при кліку поза ним
        dialog.show()
    }

    override fun onBackPressed() {
        // Обробка натискання системної кнопки "Back"
        if (overlay.visibility == View.VISIBLE) {
            finish() // Закриваємо активність, якщо прозорий фон видимий
        } else {
            super.onBackPressed()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri = data.data!!
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                largePhotoImageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                openGallery()
            }
        }
    }
}
