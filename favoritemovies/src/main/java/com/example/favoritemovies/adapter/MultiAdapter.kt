package com.example.ujianke4dicoding.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import com.example.favoritemovies.R
import com.example.favoritemovies.getColumnString
import com.example.ujianke4dicoding.response.COLUMN_NAME
import com.example.ujianke4dicoding.response.COLUMN_POSTER
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class MultiAdapter(val context: Context, val c: Cursor?, val autoRequery: Boolean) :
    CursorAdapter(context, c, autoRequery) {
    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {
        val v = LayoutInflater.from(context).inflate(R.layout.item_row, p2, false)
        return v
    }

    override fun bindView(view: View?, p1: Context?, cursor: Cursor?) {
        cursor?.apply {
            view?.apply {
                Picasso.get()
                    .load(getColumnString(cursor, COLUMN_POSTER))
                    .into(imgMovies)

                tvMovies.text = getColumnString(cursor, COLUMN_NAME)
            }
        }
    }


}

