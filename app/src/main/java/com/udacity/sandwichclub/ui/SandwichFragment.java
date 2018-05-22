package com.udacity.sandwichclub.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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

        if (getArguments() != null) {
            sandwich = getArguments().getParcelable(ARG_SANDWICH);
        } else {
            //noinspection ConstantConditions
            Log.d(TAG, "Sandwich not received from " + getActivity().getLocalClassName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        populateUI();
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_error);

        //noinspection ConstantConditions
        Glide.with(getActivity())
                .setDefaultRequestOptions(options)
                .load(sandwich.getImage())
                .into(sandwichImageView);

        return view;
    }

    private void populateUI() {
        nameTextView.setText(parseString(sandwich.getMainName()));
        akaTextView.setText(parseString(sandwich.getAlsoKnownAs()));
        originTextView.setText(parseString(sandwich.getPlaceOfOrigin()));
        descTextView.setText(parseString(sandwich.getDescription()));
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

    private String parseString(String string) {
        if (TextUtils.isEmpty(string)) {
            return getString(R.string.detail_unavailable_error_message);
        }

        return string;
    }
}
