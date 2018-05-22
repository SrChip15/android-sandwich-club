package com.udacity.sandwichclub.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

public class SandwichAdapter extends ArrayAdapter<Sandwich> {
    /* Class variables */
    private LayoutInflater inflater;
    private List<Sandwich> sandwiches;

    /* Class Constants */
    private static final String TAG = SandwichAdapter.class.getSimpleName();

    public SandwichAdapter(@NonNull Context context, List<Sandwich> sandwiches) {
        super(context, 0, sandwiches);
        inflater = LayoutInflater.from(context);
        this.sandwiches = sandwiches;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_view, parent, false);
        }

        TextView sandwichQuickInfo = convertView.findViewById(R.id.sandwich_quick_info_tv);
        Sandwich sandwich = sandwiches.get(position);
        if (sandwich != null) {
            sandwichQuickInfo.setText(sandwich.getMainName());
            //todo: Affix circular image of sandwich
        } else {
            Log.e(TAG, "Sandwich at position " + position + " parsed as null");
        }

        return convertView;
    }
}
