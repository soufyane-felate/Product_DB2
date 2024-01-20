package com.example.felatesoufyanedb001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

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

            val id = idEditText.text.toString().toInt()
            val name = nameEditText.text.toString()
            val price = priceEditText.text.toString().toDouble()
            val image = imageEditText.text.toString()

            val dbHelper = DataBase(this)
            dbHelper.addData(id, name, price, image)

            setResult(RESULT_OK)

            finish()
        }

        backButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
