package com.example.felatesoufyanedb001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DataBase

    private val addProductLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                loadDataFromDatabase()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addNewButton: Button = findViewById(R.id.addNew)
        addNewButton.setOnClickListener {
            val intent = Intent(this, ActivityAdd::class.java)
            addProductLauncher.launch(intent)
        }

        recyclerView = findViewById(R.id.recycler)
        productAdapter = ProductAdapter(mutableListOf()) {
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter

        dbHelper = DataBase(this)

        loadDataFromDatabase()
    }

    private fun loadDataFromDatabase() {
        val productList = dbHelper.getAllProducts()

        productAdapter.submitList(productList)
    }
}
