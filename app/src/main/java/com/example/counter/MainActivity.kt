package com.example.counter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var list: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        list = findViewById(R.id.list)
        val minusFabButton: FloatingActionButton = findViewById(R.id.minus)
        val plusFabButton: FloatingActionButton = findViewById(R.id.plus)
        val viewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.uiStateLiveData.observe(this) { uiState ->
            when (uiState) {
                is UIState.EmptyList -> Unit
                is UIState.FilledList -> {
                    val adapter =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, uiState.list)
                    list?.adapter = adapter
                }
            }
        }
        plusFabButton.setOnClickListener {
            viewModel.intentLiveData.value = Intent.AddItem
        }

        minusFabButton.setOnClickListener {
            viewModel.removeItem()
        }
    }
}