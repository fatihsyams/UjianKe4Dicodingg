package com.example.ujianke4dicoding.screen.favorite.moviefav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ujianke4dicoding.database.AppDatabase
import com.example.ujianke4dicoding.response.ResultsItem
import kotlinx.coroutines.launch

class MoviesFavViewModel(application: Application) : AndroidViewModel(application) {
    val db = AppDatabase.getDB(application)

    var favoriteMovies = MutableLiveData<List<ResultsItem>>()


    fun getFavoriteMovies(){
        favoriteMovies.value = db.movieDao().getAll()
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