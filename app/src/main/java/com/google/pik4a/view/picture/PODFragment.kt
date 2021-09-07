package com.google.pik4a.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.pik4a.R
import com.google.pik4a.databinding.FragmentMainBinding
import com.google.pik4a.viewmodel.PODViewModel
import com.google.pik4a.viewmodel.PictureOfTheDayData

class PODFragment:Fragment() {
    private var _binding: FragmentMainBinding? = null
    lateinit var binding:FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Error -> {//TODO HW
                Toast.makeText(context, "PODData.Error", Toast.LENGTH_LONG).show()
            }
            is PictureOfTheDayData.Loading -> {
                Toast.makeText(context, "PODData.Loading", Toast.LENGTH_LONG).show()
            }
            is PictureOfTheDayData.Success -> {
                binding.imageView.load(data.serverResponseData.url) {
                    error(R.drawable.ic_load_error_vector)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = PODFragment()
    }
}