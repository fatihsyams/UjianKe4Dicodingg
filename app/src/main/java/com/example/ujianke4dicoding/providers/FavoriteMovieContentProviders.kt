package com.example.ujianke4dicoding.providers

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.content.UriMatcher
import com.example.ujianke4dicoding.database.AppDatabase
import com.example.ujianke4dicoding.providers.ContractProviders.Companion.ALL_ITEMS
import com.example.ujianke4dicoding.providers.ContractProviders.Companion.AUTHORITY
import com.example.ujianke4dicoding.providers.ContractProviders.Companion.CONTENT_PATH
import com.example.ujianke4dicoding.providers.ContractProviders.Companion.ITEMS
import com.example.ujianke4dicoding.response.ResultsItem

class FavoriteMovieContentProviders : ContentProvider() {

    private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)


    init {
        MATCHER.addURI(AUTHORITY, CONTENT_PATH, ALL_ITEMS)
        MATCHER.addURI(AUTHORITY, CONTENT_PATH + "/*", ITEMS)
    }


    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return null
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        val code = MATCHER.match(p0)
        if (code == ALL_ITEMS || code == ITEMS) {
            if (context == null) {
                return null
            }
            val movie = AppDatabase.getDB(context!!).movieDao()
            val cursor: Cursor
            if (code == ALL_ITEMS) {
                cursor = movie.getAllByCursor()
            } else {
                cursor = movie.getItem(ContentUris.parseId(p0).toInt())
            }
            cursor.setNotificationUri(context!!.getContentResolver(), p0)
            return cursor
        } else {
            throw IllegalArgumentException("Unknown URI: " + p0)
        }
    }


    override fun onCreate(): Boolean {
        return true
    }

    override fun update(uri: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        when (MATCHER.match(uri)) {
            ALL_ITEMS -> {
                throw  IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            }

            ITEMS -> {
                if (context == null) {
                    return 0
                }
                val movie = ResultsItem().fromContentValues(p1!!)
                movie.id = ContentUris.parseId(uri).toInt()
                val count = AppDatabase.getDB(context!!).movieDao()
                    .update(movie)
                context!!.getContentResolver().notifyChange(uri, null)
                return count
            }
            else ->
                throw  IllegalArgumentException("Unknown URI: " + uri)
        }
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        when (MATCHER.match(uri)) {
            ALL_ITEMS -> return "vnd.android.cursor.dir/$AUTHORITY/$CONTENT_PATH"
            ITEMS -> return "vnd.android.cursor.item/$AUTHORITY/$CONTENT_PATH"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

}

