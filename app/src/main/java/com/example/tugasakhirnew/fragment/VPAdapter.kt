package com.example.tugasakhirnew.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.appbar.AppBarLayout

class VPAdapter (FM:FragmentManager) :FragmentPagerAdapter(FM)
{
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0->  {PersonalFragment()}
            1-> {AgensiFragment()}
            else-> {return UrgensiFragment()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "personal"
            1 -> "Agensi"
            else -> return  "Urgent"
        }
    }
}