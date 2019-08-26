package com.example.ujianke4dicoding.screen.detail


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianke4dicoding.database.AppDatabase
import com.example.ujianke4dicoding.response.ResultsItem
import kotlinx.coroutines.launch


class DetailViewModel(application: Application) : AndroidViewModel(application)  {
    val db = AppDatabase.getDB(application)

    fun insertMovie(item : ResultsItem) = viewModelScope.launch {

        val movie = ResultsItem(
            id = item.id,
            overview = item.overview,
            title = item.title,
            posterPath = item.posterPath,
            isFavorite = item.isFavorite
        )
        db.movieDao().insertMovies(movie)
    }

    fun deleteMovie(item : ResultsItem) = viewModelScope.launch {
        val movie = ResultsItem(
            id = item.id,
            overview = item.overview,
            title = item.title,
            posterPath = item.posterPath
        )
        db.movieDao().deleteMovies(movie)
    }


}