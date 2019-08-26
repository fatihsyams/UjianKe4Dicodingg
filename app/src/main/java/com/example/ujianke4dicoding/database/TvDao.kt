package com.example.ujianke4dicoding.database

import androidx.room.*
import com.example.ujianke4dicoding.response.ResultsItem
import com.example.ujianke4dicoding.response.responsetv.ResultsItemss

@Dao
interface TvDao {
    @Query("SELECT * FROM resultsitemss")
    fun getAll(): List<ResultsItemss>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: ResultsItemss)

    @Delete
    fun deleteMovies(movies: ResultsItemss)

    @Query("UPDATE resultsitem SET isFavorite=:isFavorite WHERE id = :idMovie")
    fun updateFavoriteMovie(isFavorite:Boolean, idMovie:Int)
}