package com.udacity.sandwichclub.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Parcelable {

    protected Sandwich(Parcel in) {
        mainName = in.readString();
        alsoKnownAs = in.createStringArrayList();
        placeOfOrigin = in.readString();
        description = in.readString();
        image = in.readString();
        ingredients = in.createStringArrayList();
    }

    public static final Creator<Sandwich> CREATOR = new Creator<Sandwich>() {
        @Override
        public Sandwich createFromParcel(Parcel in) {
            return new Sandwich(in);
        }

        @Override
        public Sandwich[] newArray(int size) {
            return new Sandwich[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mainName);
        dest.writeList(alsoKnownAs);
        dest.writeString(this.placeOfOrigin);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeList(this.ingredients);
    }

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin,
                    String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Sandwich object details: " + "\n" +
                "---------------------------" + "\n" +
                this.mainName + ",\n" +
                this.alsoKnownAs.size() + " aka" + ",\n" +
                this.placeOfOrigin + ",\n" +
                this.description + ",\n" +
                this.image + ",\n" +
                this.ingredients.size() + " ingredients" + ",\n" +
                "---------------------------";
    }

    public static List<Sandwich> fromJson(@NonNull String[] data) {
        List<Sandwich> sandwiches = new ArrayList<>();
        for (String sandwich : data) {
            sandwiches.add(JsonUtils.parseSandwichJson(sandwich));
        }

        return sandwiches;
    }
}
