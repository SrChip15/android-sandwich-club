package com.udacity.sandwichclub.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udacity.sandwichclub.R
import com.udacity.sandwichclub.model.Sandwich

class SandwichAdapter(private val context: Context, private val sandwiches: ArrayList<Sandwich>) :
    ArrayAdapter<Sandwich>(context, 0, sandwiches) {
    companion object {
        val TAG = SandwichAdapter::class.simpleName
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false)

        val name: TextView = view.findViewById(R.id.sandwich_quick_info_tv)
        val image: ImageView = view.findViewById(R.id.sandwich_quick_info_iv);

        val sandwich = sandwiches[position]
        if (sandwich != null) {
            name.text = sandwich.mainName

            //todo: Affix circular image of sandwich
            val options = RequestOptions()
                    .error(R.drawable.ic_error)
                    .fitCenter()
                    .circleCrop()

            Glide.with(context)
                    .setDefaultRequestOptions(options)
                    .load(sandwich.image)
                    .into(image)

        } else {
            Log.e(TAG, "Sandwich at position $position parsed as null")
        }

        return view
    }
}
