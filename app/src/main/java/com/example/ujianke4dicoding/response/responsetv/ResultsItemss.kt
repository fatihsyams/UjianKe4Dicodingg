package com.example.ujianke4dicoding.response.responsetv

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "resultsitemss")
data class ResultsItemss(

	@PrimaryKey(autoGenerate = true)
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	var isFavorite:Boolean = false
) : Serializable