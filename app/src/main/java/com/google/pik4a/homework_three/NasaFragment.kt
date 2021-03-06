package com.google.pik4a.homework_three

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.pik4a.R
import com.google.pik4a.databinding.FragmentMainBinding
import com.google.pik4a.view.MainActivity
import com.google.pik4a.view.picture.BottomNavigationDrawerFragment
import com.google.pik4a.view.picture.PODFragment
import com.google.pik4a.viewmodel.PODViewModel
import com.google.pik4a.viewmodel.PictureOfTheDayData

class NasaFragment: Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var radioGroup: RadioGroup

    private var _binding: FragmentMainBinding? = null
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater)
//        setActionBar()
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.sendServerRequest()
        binding.inputLayout.setEndIconOnClickListener {
            val i = Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            }
            startActivity(i)
        }
        bottomSheetBehavior = BottomSheetBehavior.from(binding.includeLayout.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED



        binding.radioGroup.setOnCheckedChangeListener { group: RadioGroup?, checkId: Int ->
            val checkedRadioButton = group?.findViewById(group.checkedRadioButtonId) as? RadioButton
            checkedRadioButton?.let {

                if (checkedRadioButton.isChecked)

                    Toast.makeText(
                        requireContext(),
                        "RadioGroup: ${group?.contentDescription} RadioButton: ${checkedRadioButton?.text}",
                        Toast.LENGTH_LONG


                    ).show()
            }
        }
    }


    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }


    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Error -> {
                R.drawable.ic_baseline_error_24
            }
            is PictureOfTheDayData.Loading -> {
                binding.imageView.load(R.drawable.progress_image_animation)
            }
            is PictureOfTheDayData.Success -> {
                binding.imageView.load(data.serverResponseData.url) {
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.progress_image_animation)
                }
                binding.includeLayout.bottomSheetDescriptionHeader.text =
                    data.serverResponseData.explanation
                binding.includeLayout.bottomSheetDescription.text =
                    data.serverResponseData.explanation
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = NasaFragment()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.app_bar_fav -> {
                Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
            }
            R.id.app_bar_settings -> {
                Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    private var isMain = true
//    private fun setActionBar() {
//        (context as MainActivity).setSupportActionBar(binding.bottomAppBar)
//        setHasOptionsMenu(true)
//
//    }


}