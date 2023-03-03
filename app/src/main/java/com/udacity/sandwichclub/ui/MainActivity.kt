package com.udacity.sandwichclub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.sandwichclub.R
import com.udacity.sandwichclub.databinding.ActivityMainBinding
import com.udacity.sandwichclub.model.Sandwich
import com.udacity.sandwichclub.ui.adapter.SandwichAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sandwiches = resources.getStringArray(R.array.sandwich_details)
        recyclerView.adapter = SandwichAdapter(Sandwich.fromJson(sandwiches))
        recyclerView.setHasFixedSize(true)
    }
}
