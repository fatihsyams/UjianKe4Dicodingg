package com.example.ujianke4dicoding.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import android.content.ContentValues
import androidx.room.ColumnInfo

const val COLUMN_NAME = "name"
const val COLUMN_ID = "id"
const val COLUMN_POSTER = "poster"
data class ResultsItem(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "poster")
    var posterPath: String? = null,

    var isFavorite: Boolean = false

) : Serializable
