package com.example.layoutsexample.ui.relative

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.layoutsexample.R

class RelativeFragment : Fragment() {

    private lateinit var relativeViewModel: RelativeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        relativeViewModel =
            ViewModelProvider(this).get(RelativeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_relative, container, false)
        val textView: TextView = root.findViewById(R.id.text_relative)
        relativeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}