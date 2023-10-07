package com.dogactnrvrdi.movietime.view.HomeScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    // Binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binding
        _binding = FragmentHomeBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}