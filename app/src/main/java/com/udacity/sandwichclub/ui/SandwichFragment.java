package com.udacity.sandwichclub.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SandwichFragment extends Fragment {
    /* Class Constants */
    private static final String ARG_SANDWICH = "sandwich";
    private static final String TAG = SandwichFragment.class.getSimpleName();

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

    public static SandwichFragment newInstance(Sandwich sandwich) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_SANDWICH, sandwich);

        SandwichFragment fragment = new SandwichFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sandwich = getArguments().getParcelable(ARG_SANDWICH);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        populateUI();
        Picasso.with(getActivity())
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
                    }
                });

        return view;
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
        if (listOfWords.size() > 0) {
            listOfOtherNames = TextUtils.join(", ", listOfWords);
        } else {
            return getString(R.string.detail_unavailable_error_message);
        }

        return listOfOtherNames;
    }
}
