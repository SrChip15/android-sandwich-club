package com.udacity.sandwichclub.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.udacity.sandwichclub.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        // SandwichAdapter adapter = new SandwichAdapter(this, Sandwich.fromJson(sandwiches));
        //
        // // Simplification: Using a ListView instead of a RecyclerView
        // ListView listView = findViewById(R.id.sandwiches_listview);
        // listView.setAdapter(adapter);
        // listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //     @Override
        //     public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //         launchDetailActivity(position);
        //     }
        // });
    }

    // private void launchDetailActivity(int position) {
    //     Intent intent = SandwichPagerActivity.newIntent(this, position);
    //     startActivity(intent);
    // }
}
