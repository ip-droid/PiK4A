package com.google.pik4a.view.picture

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.QuoteSpan
import android.text.style.TypefaceSpan
import android.view.*
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.pik4a.R
import com.google.pik4a.databinding.FragmentMainBinding
import com.google.pik4a.homework_three.ApiActivity
import com.google.pik4a.view.MainActivity
import com.google.pik4a.viewmodel.PODViewModel
import com.google.pik4a.viewmodel.PictureOfTheDayData

class PODFragment : Fragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var radioGroup: RadioGroup

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater)
        setActionBar()
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
                R.drawable.ic_baseline_downloading_24
            }
            is PictureOfTheDayData.Success -> {
                binding.imageView.load(data.serverResponseData.url) {
                    error(R.drawable.ic_load_error_vector)
                }
                data.serverResponseData.explanation?.let {
                    //binding.includeLayoutTv.textView.text = it
                    /*binding.includeLayoutTv.textView.typeface =
                        Typeface.createFromAsset(requireActivity().assets,"font/Robus-BWqOd.otf")

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        binding.includeLayoutTv.textView.typeface = resources.getFont(R.font.azeret)
                    }*/


                    //маркировка через HTML
                    /*val text = "My text <ul><li>bullet one</li><li>bullet two</li></ul>"
                    binding.includeLayoutTv.textView.text = Html.fromHtml(text,Html.FROM_HTML_MODE_COMPACT)*/

                    //маркировка через Spannable
                    /* val spannable = SpannableStringBuilder("My text \nbullet one \nbullet two")
                     spannable.setSpan(BulletSpan(20,resources.getColor(R.color.colorAccent)),
                         9,18,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                     spannable.setSpan(BulletSpan(20,resources.getColor(R.color.colorAccent)),
                         21,spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)*/
                    val spannableStart = SpannableStringBuilder(it)

                    binding.includeLayoutTv.textView.setText(
                        spannableStart,
                        TextView.BufferType.EDITABLE
                    )
                    val spannable = binding.includeLayoutTv.textView.text as SpannableStringBuilder


                    val start = 1
                    val end = 3



                    spannable.setSpan(
                        ForegroundColorSpan(resources.getColor(R.color.colorAccent)), start,
                        end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannable.insert(end, "x")
                    spannable.insert(start, "x")

                    spannable.setSpan(
                        ForegroundColorSpan(resources.getColor(R.color.colorPrimary)), 5,
                        20, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )

                    spannable.setSpan(
                        ForegroundColorSpan(resources.getColor(R.color.colorAccent)), 20,
                        35, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        spannable.setSpan(
                            TypefaceSpan(resources.getFont(R.font.azeret)), 20,
                            25, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                        )
                    }

                    spannable.setSpan(
                        ForegroundColorSpan(resources.getColor(R.color.colorPrimary)), 0,
                        spannable.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    spannable.setSpan(
                        QuoteSpan(resources.getColor(R.color.colorAccent)), 0,
                        5, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    spannable.setSpan(
                        BackgroundColorSpan(resources.getColor(R.color.colorAccent)), 1,
                        5, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    spannable.setSpan(
                        QuoteSpan(resources.getColor(R.color.colorAccent)), 14,
                        25, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    spannable.setSpan(
                        QuoteSpan(resources.getColor(R.color.colorAccent)), 27,
                        29, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )


                    val request = FontRequest("com.google.android.gms.fonts",
                        "com.google.android.gms","Aguafina Script",R.array.com_google_android_gms_fonts_certs)
                    val fontCallback = object : FontsContractCompat.FontRequestCallback(){
                        override fun onTypefaceRetrieved(typeface: Typeface?) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                typeface?.let {
                                    spannable.setSpan(TypefaceSpan(it),0,
                                        spannable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                                }
                            }
                        }
                    }
                    FontsContractCompat.requestFont(requireContext(),request,fontCallback,
                        Handler(Looper.getMainLooper())
                    )



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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.app_bar_fav -> {
                Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, ApiActivity::class.java))
            }
            R.id.app_bar_settings -> {
                Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
            }
            // у нашего бургера такой вот id внутри android
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private var isMain = true
    private fun setActionBar() {
        (context as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)

    }


}