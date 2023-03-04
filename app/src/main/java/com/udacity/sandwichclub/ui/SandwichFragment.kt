package com.udacity.sandwichclub.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udacity.sandwichclub.R
import com.udacity.sandwichclub.databinding.FragmentSandwichBinding
import com.udacity.sandwichclub.model.Sandwich


class SandwichFragment : Fragment() {

    companion object {
        const val SANDWICH = "sandwich"
    }

    private var _binding: FragmentSandwichBinding? = null

    private lateinit var sandwichImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var akaTextView: TextView
    private lateinit var originTextView: TextView
    private lateinit var descTextView: TextView
    private lateinit var ingredientsTextView: TextView
    private lateinit var sandwich: Sandwich

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?. let {
            sandwich = it.getParcelable(SANDWICH)!!
        }
    }

    @Suppress("RedundantNullableReturnType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSandwichBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sandwichImageView = binding.sandwichImageView
        nameTextView = binding.nameTextView
        akaTextView = binding.akaTextView
        originTextView = binding.originPlaceTextView
        descTextView = binding.descTextView
        ingredientsTextView = binding.ingredientsTextView

        populateUI()
        val options = RequestOptions().centerCrop().error(R.drawable.ic_error)

        Glide.with(view.context)
            .setDefaultRequestOptions(options)
            .load(sandwich.image)
            .into(sandwichImageView)
    }

    private fun populateUI() {
        nameTextView.text = parseString(sandwich.mainName)
        akaTextView.text = parseString(sandwich.alsoKnownAs)
        originTextView.text = parseString(sandwich.placeOfOrigin)
        descTextView.text = parseString(sandwich.description)
        ingredientsTextView.text = parseString(sandwich.ingredients)
    }

    private fun parseString(listOfWords: List<String>): String {
        var listOfOtherNames = ""
        if (listOfWords.isNotEmpty()) {
            listOfOtherNames = TextUtils.join(", ", listOfWords);
        } else {
            return getString(R.string.detail_unavailable_error_message);
        }

        return listOfOtherNames
    }

    private fun parseString(string: String): String {
        if (TextUtils.isEmpty(string)) {
            return getString(R.string.detail_unavailable_error_message)
        }

        return string
    }
}