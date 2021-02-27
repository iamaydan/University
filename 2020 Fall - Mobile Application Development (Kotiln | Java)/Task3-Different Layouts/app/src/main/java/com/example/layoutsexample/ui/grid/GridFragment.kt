package com.example.layoutsexample.ui.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.layoutsexample.R

class GridFragment : Fragment() {

    private lateinit var gridViewModel: GridViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gridViewModel =
            ViewModelProvider(this).get(GridViewModel::class.java)
        //        val textView: TextView = root.findViewById(R.id.text_grid)
//        gridViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return inflater.inflate(R.layout.fragment_grid, container, false)
    }
}