package com.example.ujianke4dicoding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ujianke4dicoding.screen.favorite.moviefav.MoviesFavFragment
import com.example.ujianke4dicoding.screen.favorite.tvshowfav.TvShowfavFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        TvShowfavFragment(),
        MoviesFavFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "TV SHOW"
            else -> "MOVIES"

        }
    }
}