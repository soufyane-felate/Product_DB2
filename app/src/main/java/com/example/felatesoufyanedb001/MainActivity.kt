package com.example.felatesoufyanedb001
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DataBase

    private val addProductLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val id = result.data?.getIntExtra("id", -1)
            val name = result.data?.getStringExtra("name")
            val price = result.data?.getDoubleExtra("price", -1.0)
            val image = result.data?.getStringExtra("image")

            if (id != null && name != null && price != null && image != null) {
                val newProduct = Product(id, name, price, image)
                productAdapter.proList.add(newProduct)
                productAdapter.notifyItemInserted(productAdapter.proList.size - 1)
            } else {
                Toast.makeText(this, "Error getting data from AddActivity", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addNewButton: Button = findViewById(R.id.addNew)
        addNewButton.setOnClickListener {
            val intent = Intent(this, ActivityAdd::class.java)
            addProductLauncher.launch(intent)

            // Apply slide-in animation
            overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left)
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

        productAdapter.proList.addAll(productList)
        productAdapter.notifyDataSetChanged()
    }
}
