package com.example.ujianke4dicoding.screen.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianke4dicoding.Load
import com.example.ujianke4dicoding.Service
import com.example.ujianke4dicoding.response.MoviesResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException


class MoviesViewModel : ViewModel() {

    private val service = Service.create()

    private val _moviesLoad = MutableLiveData<Load<MoviesResponse>>()
    val moviesLoad = _moviesLoad as LiveData<Load<MoviesResponse>>

    init {
        _moviesLoad.value = Load.Uninitialized
    }

    fun getDataMovies() = viewModelScope.launch {
        _moviesLoad.value = Load.Loading

        try {
            val response = service.getMovie()
            _moviesLoad.value = Load.Success(response)
            Log.d("TAG", "DATA DAPAT CUY")

        } catch (e: HttpException) {
            _moviesLoad.value = Load.Fail(e)
            Log.d("TAG", "DATA NYA GA ADA")
        }
    }


}