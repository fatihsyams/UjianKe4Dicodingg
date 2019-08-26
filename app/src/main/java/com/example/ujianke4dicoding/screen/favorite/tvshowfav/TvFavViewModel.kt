package com.example.ujianke4dicoding.screen.favorite.tvshowfav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.ujianke4dicoding.database.AppDatabase
import com.example.ujianke4dicoding.response.responsetv.ResultsItemss

class TvFavViewModel(application: Application) : AndroidViewModel(application) {
    val db = AppDatabase.getDB(application)

    var favoriteMovies = MutableLiveData<List<ResultsItemss>>()

    fun getFavoriteMovies() {
        favoriteMovies.value = db.tvDao().getAll()
    }
}
