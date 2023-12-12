package com.example.pr_final_303

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pr_final_303.Adapter.ProductAdapter
import com.example.pr_final_303.model.Product
import com.example.pr_final_303.model.ProductRepository

class ProductListActivity : AppCompatActivity() {

    private val productRepository by lazy { ProductRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        // Inserta un producto de ejemplo (puedes hacer esto en otro lugar según tus necesidades)
        val sampleProduct = Product(name = "Ejemplo", price = 10.99)
        productRepository.insertProduct(sampleProduct)

        // Obtiene la lista de productos y muestra en un RecyclerView
        val productList = productRepository.getAllProducts()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductAdapter(productList)
    }
}