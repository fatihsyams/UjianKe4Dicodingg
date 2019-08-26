package com.example.ujianke4dicoding.screen.favorite.moviefav


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
import com.example.ujianke4dicoding.response.ResultsItem
import kotlinx.android.synthetic.main.fragment_movies_fav.*
class MoviesFavFragment : Fragment() {

    val movieFavViewModel by lazy {
        ViewModelProviders.of(this).get(MoviesFavViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_fav, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        movieFavViewModel.favoriteMovies.observe(this, Observer {
            it?.let {
                showFavoriteMovies(it)
                Toast.makeText(context, "jumlah data : ${it.size} ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun showFavoriteMovies(datas:List<ResultsItem>){
        recFavv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MultiAdapter(context, datas)
        }
    }

    override fun onResume() {
        super.onResume()
        movieFavViewModel.getFavoriteMovies()
    }
}
