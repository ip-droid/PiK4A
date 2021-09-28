package com.google.pik4a.view.picture

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.pik4a.R
import com.google.pik4a.animation.AnimationsActivity
import com.google.pik4a.animation.AnimationsActivityBonus
import com.google.pik4a.databinding.BottonNavigationLayoutBinding
import com.google.pik4a.recycler.RecyclerActivity

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottonNavigationLayoutBinding? = null
    val binding: BottonNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottonNavigationLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { it->
            when(it.itemId){
                R.id.navigation_one ->{
                    activity?.let {
                        startActivity(Intent(it, AnimationsActivity::class.java))
                    }
                }
                R.id.navigation_two ->{
                    activity?.let {
                        //startActivity(Intent(it,AnimationsActivity::class.java))
                        startActivity(Intent(it, AnimationsActivityBonus::class.java))
                    }
                }
                R.id.navigation_third ->{
                    activity?.let {
                        startActivity(Intent(it, RecyclerActivity::class.java))
                    }
                }
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    companion object {
        fun newInstance() = BottomNavigationDrawerFragment()
    }
}