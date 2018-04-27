package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    /* Class Constants */
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final String TAG = DetailActivity.class.getSimpleName();

    /* Class variables */
    @BindView(R.id.name_text_view) TextView nameTextView;
    @BindView(R.id.aka_text_view) TextView akaTextView;
    @BindView(R.id.origin_place_text_view) TextView originTextView;
    @BindView(R.id.desc_text_view) TextView descTextView;
    @BindView(R.id.ingredients_text_view) TextView ingredientsTextView;
    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        //ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        @SuppressWarnings("ConstantConditions") // Intent is already checked for null
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        /*Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);*/

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        nameTextView.setText(sandwich.getMainName());
        akaTextView.setText(parseString(sandwich.getAlsoKnownAs()));
        originTextView.setText(sandwich.getPlaceOfOrigin());
        descTextView.setText(sandwich.getDescription());
        ingredientsTextView.setText(parseString(sandwich.getIngredients()));
    }

    private String parseString(List<String> listOfWords) {
        String listOfOtherNames = null;
        if (listOfWords.size() >0) {
            listOfOtherNames = TextUtils.join(", ", listOfWords);
            Log.i(TAG, "List String: " + listOfOtherNames);
        }
        return listOfOtherNames;
    }
}
