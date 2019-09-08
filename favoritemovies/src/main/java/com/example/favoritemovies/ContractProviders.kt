package com.example.favoritemovies

import android.database.Cursor
import android.net.Uri

class ContractProviders {
    companion object {
        val AUTHORITY = "com.example.ujianke4dicoding"
        val CONTENT_PATH = "resultsitem"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$CONTENT_PATH")
    }


}

fun getColumnString(c: Cursor, columnName: String): String {
    return c.getString(c.getColumnIndex(columnName))
}

