package com.example.felatesoufyanedb001

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val addButton: Button = findViewById(R.id.add)
        val backButton: Button = findViewById(R.id.back)

        addButton.setOnClickListener {
            val idEditText: EditText = findViewById(R.id.id)
            val nameEditText: EditText = findViewById(R.id.name)
            val priceEditText: EditText = findViewById(R.id.price)
            val imageEditText: EditText = findViewById(R.id.image)

            val idInput = idEditText.text.toString()
            val name = nameEditText.text.toString()
            val priceInput = priceEditText.text.toString()
            val image = imageEditText.text.toString()

            val dbHelper = DataBase(this)

            if (idInput.isEmpty() || name.isEmpty() || priceInput.isEmpty() || image.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val id = idInput.toInt()
                val price = priceInput.toDouble()

                if (dbHelper.checkIfIdExists(id)) {
                    Toast.makeText(this, "ID already exists!", Toast.LENGTH_SHORT).show()
                } else {
                    dbHelper.addData(id, name, price, image)

                    val intent = Intent().apply {
                        putExtra("id", id)
                        putExtra("name", name)
                        putExtra("price", price)
                        putExtra("image", image)
                    }

                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }

        backButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
