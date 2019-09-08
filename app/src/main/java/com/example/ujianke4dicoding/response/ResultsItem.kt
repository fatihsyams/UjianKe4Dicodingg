package com.example.ujianke4dicoding.response

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

const val COLUMN_NAME = "name"
const val COLUMN_ID = "id"
const val COLUMN_POSTER = "poster"

@Entity(tableName = "resultsitem")
data class ResultsItem(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id")
    var id: Int? = null,

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String? = null,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    var title: String? = null,


    @ColumnInfo(name = "poster")
    @field:SerializedName("poster_path")
    var posterPath: String? = null,

    var isFavorite: Boolean = false

) : Serializable {
    fun fromContentValues(values: ContentValues): ResultsItem {

        val movie = ResultsItem()
        if (values.containsKey(COLUMN_ID)) {
            movie.id = values.getAsInteger(COLUMN_ID)
        }
        if (values.containsKey(COLUMN_NAME)) {
            movie.title = values.getAsString(COLUMN_NAME)
        }
        if (values.containsKey(COLUMN_POSTER)) {
            movie.posterPath = values.getAsString(COLUMN_POSTER)
        }
        return movie
    }
}