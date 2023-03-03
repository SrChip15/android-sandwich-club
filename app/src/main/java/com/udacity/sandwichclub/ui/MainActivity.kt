package com.udacity.sandwichclub.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.udacity.sandwichclub.R
import com.udacity.sandwichclub.model.Sandwich
import com.udacity.sandwichclub.ui.adapter.SandwichAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sandwiches = resources.getStringArray(R.array.sandwich_details)
        val adapter = SandwichAdapter(this, Sandwich.fromJson(sandwiches) as ArrayList<Sandwich>)

        // Simplification: Using a ListView instead of a RecyclerView
        val listView: ListView = findViewById(R.id.sandwiches_listview)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SandwichPagerActivity::class.java)
            startActivity(intent)

        }
    }
}
