package com.google.pik4a.homework_three

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val EARTH =0
private const val MARS =1
private const val SYSTEM =2
private const val NASA =3

class ViewPagerAdapter(private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(EarthFragment(),MarsFragment(),SystemFragment(),NasaFragment())

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment { // TODO алгоритмически решить проблему Степана
        return when(position){
            0->fragments[EARTH]
            1->fragments[MARS]
            2->fragments[SYSTEM]
            3->fragments[NASA]
            else ->fragments[EARTH]
        }
    }

    override fun getPageTitle(position: Int): String {
        return when(position){
            0->"Earth"
            1->"Mars"
            2->"System"
            3->"Nasa"
            else ->"Earth"
        }
    }

}