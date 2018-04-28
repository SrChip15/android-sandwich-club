package com.udacity.sandwichclub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.R;
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
    @BindView(R.id.sandwich_image_view)
    ImageView sandwichImageView;
    @BindView(R.id.progressBar)
    ProgressBar imageLoadBar;
    @BindView(R.id.name_text_view)
    TextView nameTextView;
    @BindView(R.id.aka_text_view)
    TextView akaTextView;
    @BindView(R.id.origin_place_text_view)
    TextView originTextView;
    @BindView(R.id.desc_text_view)
    TextView descTextView;
    @BindView(R.id.ingredients_text_view)
    TextView ingredientsTextView;
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

        // Intent is already checked for null, so suppress null pointer exception warning
        @SuppressWarnings("ConstantConditions")
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
        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.drawable.ic_error)
                .into(sandwichImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageLoadBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        imageLoadBar.setVisibility(View.GONE);
                        Toast.makeText(
                                DetailActivity.this,
                                R.string.detail_image_error_message,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        nameTextView.setText(sandwich.getMainName());
        akaTextView.setText(parseString(sandwich.getAlsoKnownAs()));
        descTextView.setText(sandwich.getDescription());
        ingredientsTextView.setText(parseString(sandwich.getIngredients()));

        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        originTextView.setText(TextUtils.isEmpty(placeOfOrigin)? getString(R.string.detail_unavailable_error_message) : placeOfOrigin);
    }

    private String parseString(List<String> listOfWords) {
        String listOfOtherNames = null;
        if (listOfWords.size() > 0) {
            listOfOtherNames = TextUtils.join(", ", listOfWords);
        } else {
            return getString(R.string.detail_unavailable_error_message);
        }

        return listOfOtherNames;
    }
}
