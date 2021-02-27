package com.example.layoutsexample.ui.linear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.layoutsexample.R

class LinearFragment : Fragment() {

    private lateinit var linearViewModel: LinearViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        linearViewModel =
                ViewModelProvider(this).get(LinearViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_linear, container, false)
        val textView: TextView = root.findViewById(R.id.text_linear)
        linearViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}