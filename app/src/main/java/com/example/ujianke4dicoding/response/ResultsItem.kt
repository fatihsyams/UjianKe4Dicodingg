package com.example.ujianke4dicoding.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "resultsitem")
data class ResultsItem(

	@PrimaryKey(autoGenerate = true)
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	var isFavorite:Boolean = false

) : Serializable