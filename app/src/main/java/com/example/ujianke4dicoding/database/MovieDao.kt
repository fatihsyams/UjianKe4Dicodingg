package com.example.ujianke4dicoding.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ujianke4dicoding.response.ResultsItem

@Dao
interface MovieDao {
    @Query("SELECT * FROM resultsitem")
    fun getAll(): List<ResultsItem>

//    @Query("SELECT * FROM resultsitem WHERE isFavorite (:isFavorite)")
//    fun getAllFavoriteMovies(isFavorite:Boolean = true): List<ResultsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: ResultsItem)

    @Delete
    fun deleteMovies(movies: ResultsItem)

    @Query("UPDATE resultsitem SET isFavorite=:isFavorite WHERE id = :idMovie")
    fun updateFavoriteMovie(isFavorite:Boolean, idMovie:Int)
}