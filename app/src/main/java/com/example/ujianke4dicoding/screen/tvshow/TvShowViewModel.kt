package com.example.ujianke4dicoding.screen.tvshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujianke4dicoding.Load
import com.example.ujianke4dicoding.Service
import com.example.ujianke4dicoding.response.responsetv.TvResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TvShowViewModel : ViewModel() {

    private val service = Service.create()

    private val _tvLoad = MutableLiveData<Load<TvResponse>>()
    val tvLoad = _tvLoad as LiveData<Load<TvResponse>>

    init {
        _tvLoad.value = Load.Uninitialized
    }

    fun getDataTv() = viewModelScope.launch {
        _tvLoad.value = Load.Loading

        try {
            val response = service.getTv()
            _tvLoad.value = Load.Success(response)
            Log.d("tv", "DATA DAPAT")
        } catch (e: HttpException) {
            _tvLoad.value = Load.Fail(e)
            Log.d("tv", "DATA GA DAPAAT")
        }
    }

}