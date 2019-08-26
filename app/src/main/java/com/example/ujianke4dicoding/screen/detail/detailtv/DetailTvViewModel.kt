package com.example.ujianke4dicoding.screen.detail.detailtv

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianke4dicoding.database.AppDatabase
import com.example.ujianke4dicoding.response.ResultsItem
import com.example.ujianke4dicoding.response.responsetv.ResultsItemss
import kotlinx.coroutines.launch

class DetailTvViewModel(application: Application) : AndroidViewModel(application) {
    val db = AppDatabase.getDB(application)

    fun insertMovie(item : ResultsItemss) = viewModelScope.launch {

        val movie = ResultsItemss(
            id = item.id,
            overview = item.overview,
            name = item.name,
            posterPath = item.posterPath,
            isFavorite = item.isFavorite
        )
        db.tvDao().insertMovies(movie)
    }

    fun deleteMovie(item : ResultsItemss) = viewModelScope.launch {
        val movie = ResultsItemss(
            id = item.id,
            overview = item.overview,
            name = item.name,
            posterPath = item.posterPath
        )
        db.tvDao().deleteMovies(movie)
    }
}