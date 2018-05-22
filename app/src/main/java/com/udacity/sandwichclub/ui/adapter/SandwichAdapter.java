package com.udacity.sandwichclub.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.ImageUtils;

import java.util.List;

public class SandwichAdapter extends ArrayAdapter<Sandwich> {
    /* Class variables */
    private LayoutInflater inflater;
    private Context context;
    private List<Sandwich> sandwiches;

    /* Class Constants */
    private static final String TAG = SandwichAdapter.class.getSimpleName();

    public SandwichAdapter(@NonNull Context context, List<Sandwich> sandwiches) {
        super(context, 0, sandwiches);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.sandwiches = sandwiches;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_view, parent, false);
        }

        final TextView sandwichQuickInfo = convertView.findViewById(R.id.sandwich_quick_info_tv);
        Sandwich sandwich = sandwiches.get(position);
        if (sandwich != null) {
            sandwichQuickInfo.setText(sandwich.getMainName());

            //todo: Affix circular image of sandwich
            RequestOptions options = new RequestOptions()
                    .error(R.drawable.ic_error)
                    .fitCenter()
                    .circleCrop();

            Glide.with(context)
                    .setDefaultRequestOptions(options)
                    .load(sandwich.getImage())
                    .into(new SimpleTarget<Drawable>(ImageUtils.convertDpToPixel(75, context),
                            ImageUtils.convertDpToPixel(75, context)) {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource,
                                                    @Nullable Transition<? super Drawable> transition) {
                            sandwichQuickInfo.setCompoundDrawablesWithIntrinsicBounds(
                                    resource,
                                    null,
                                    null,
                                    null
                            );
                        }
                    });

        } else {
            Log.e(TAG, "Sandwich at position " + position + " parsed as null");
        }

        return convertView;
    }
}
