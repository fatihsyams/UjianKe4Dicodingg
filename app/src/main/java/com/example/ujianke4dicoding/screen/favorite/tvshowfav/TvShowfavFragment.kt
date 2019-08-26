package com.example.ujianke4dicoding.screen.favorite.tvshowfav


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.adapter.MultiAdapter
import com.example.ujianke4dicoding.adapter.TvAdapter
import com.example.ujianke4dicoding.response.ResultsItem
import com.example.ujianke4dicoding.response.responsetv.ResultsItemss
import com.example.ujianke4dicoding.screen.favorite.moviefav.MoviesFavViewModel
import kotlinx.android.synthetic.main.fragment_movies_fav.*
import kotlinx.android.synthetic.main.fragment_tv_showfav.*

class TvShowfavFragment : Fragment() {



    val tvViewModel by lazy {
        ViewModelProviders.of(this).get(TvFavViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_showfav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tvViewModel.favoriteMovies.observe(this, Observer {
            it?.let {
                showtvFav(it)
                Toast.makeText(context, "jumlah data : ${it.size} ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun showtvFav(data : List<ResultsItemss>) {
        recFav.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TvAdapter(activity!!, data)
        }
    }

    override fun onResume() {
        super.onResume()
        tvViewModel.getFavoriteMovies()
    }
}
