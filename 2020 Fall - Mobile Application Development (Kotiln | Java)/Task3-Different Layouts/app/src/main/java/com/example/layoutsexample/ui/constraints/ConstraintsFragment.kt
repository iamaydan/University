package com.example.layoutsexample.ui.constraints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.layoutsexample.R

class ConstraintsFragment : Fragment() {


    private lateinit var constraintsViewModel: ConstraintsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        constraintsViewModel =
            ViewModelProvider(this).get(ConstraintsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_constraints, container, false)
        val textView: TextView = root.findViewById(R.id.text_constraints)
        constraintsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

}

