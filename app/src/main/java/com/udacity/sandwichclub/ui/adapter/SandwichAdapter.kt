package com.udacity.sandwichclub.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udacity.sandwichclub.R
import com.udacity.sandwichclub.model.Sandwich
import com.udacity.sandwichclub.ui.SandwichListFragmentDirections

class SandwichAdapter(private val sandwiches: List<Sandwich>) :
    RecyclerView.Adapter<SandwichAdapter.SandwichHolder>() {

    // companion object {
    //     val TAG = SandwichAdapter::class.simpleName
    // }

    class SandwichHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.sandwich_quick_info_iv)
        val name: TextView = itemView.findViewById(R.id.sandwich_quick_info_tv)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandwichHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return SandwichHolder(itemView)
    }

    override fun getItemCount(): Int {
        return sandwiches.size
    }

    override fun onBindViewHolder(holder: SandwichHolder, position: Int) {
        val sandwich = sandwiches[position]
        holder.name.text = sandwich.mainName

        val options = RequestOptions().error(R.drawable.ic_error).fitCenter().circleCrop()

        Glide.with(holder.image.context)
            .setDefaultRequestOptions(options)
            .load(sandwich.image)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val action =
                SandwichListFragmentDirections.actionSandwichListFragmentToSandwichFragment(
                    sandwich = sandwich,
                    fragmentLabel = sandwich.mainName
                )
            holder.itemView.findNavController().navigate(action)
        }
    }
}
