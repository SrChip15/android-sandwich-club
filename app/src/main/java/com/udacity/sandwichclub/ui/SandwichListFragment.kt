package com.udacity.sandwichclub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udacity.sandwichclub.R
import com.udacity.sandwichclub.databinding.FragmentSandwichListBinding
import com.udacity.sandwichclub.model.Sandwich
import com.udacity.sandwichclub.ui.adapter.SandwichAdapter


class SandwichListFragment : Fragment() {

    private var _binding: FragmentSandwichListBinding? = null
    private lateinit var recyclerView: RecyclerView

    private val binding get() = _binding!!

    @Suppress("RedundantNullableReturnType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSandwichListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val sandwiches = resources.getStringArray(R.array.sandwich_details)
        recyclerView.adapter = SandwichAdapter(Sandwich.fromJson(sandwiches))
        recyclerView.setHasFixedSize(true)
    }

}