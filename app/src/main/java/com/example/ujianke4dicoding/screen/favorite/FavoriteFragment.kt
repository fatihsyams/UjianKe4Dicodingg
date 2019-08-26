package com.example.ujianke4dicoding.screen.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.adapter.MyPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewpager_main.adapter = MyPagerAdapter(childFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }


}
