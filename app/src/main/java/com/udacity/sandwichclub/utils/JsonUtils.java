package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    /* Class Constants */
    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {
        if (TextUtils.isEmpty(json)) {
            Log.i(TAG, "Empty JSON string passed to " + TAG + "#parseSandwichJson()");
            return null;
        }

        Sandwich sandwich = null;
        try {
            JSONObject jsonString = new JSONObject(json);
            JSONObject name = jsonString.getJSONObject("name");
            String mainName = name.getString("mainName");

            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray aka = name.getJSONArray("alsoKnownAs");
            if (aka.length() > 0) {
                for (int i = 0; i < aka.length(); i++) {
                    alsoKnownAs.add(aka.get(i).toString());
                }
            }

            String placeOfOrigin = jsonString.getString("placeOfOrigin");
            String description = jsonString.getString("description");
            String image = jsonString.getString("image");

            List<String> ingredients = new ArrayList<>();
            JSONArray rawIngredients = jsonString.getJSONArray("ingredients");
            if (rawIngredients.length() > 0) {
                for (int i = 0; i < rawIngredients.length(); i++) {
                    ingredients.add(rawIngredients.get(i).toString());
                }
            }

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return sandwich;
    }
}
