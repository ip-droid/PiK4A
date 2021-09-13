package com.google.pik4a.homework_three

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val EARTH =0
private const val MARS =1
private const val SYSTEM =2

class ViewPagerAdapter(private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(EarthFragment(),MarsFragment(),SystemFragment())

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment { // TODO алгоритмически решить проблему Степана
        return when(position){
            0->fragments[EARTH]
            1->fragments[MARS]
            2->fragments[SYSTEM]
            else ->fragments[EARTH]
        }
    }

    override fun getPageTitle(position: Int): String {
        return when(position){
            0->"Earth"
            1->"Mars"
            2->"System"
            else ->"Earth"
        }
    }

}