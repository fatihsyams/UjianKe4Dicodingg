package com.example.favoritemovies

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujianke4dicoding.adapter.MultiAdapter
import com.example.ujianke4dicoding.response.COLUMN_ID
import com.example.ujianke4dicoding.response.COLUMN_NAME
import com.example.ujianke4dicoding.response.COLUMN_POSTER
import com.example.ujianke4dicoding.response.ResultsItem
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor>{

    var queryUri = ContractProviders.CONTENT_URI
    var projection = null
    var selectionClause: String? = null
    var selectionArgs: Array<String>? = null
    var sortOrder: String? = null

    lateinit var adapter:MultiAdapter

    companion object{
        const val LOADER_MOVIES = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MultiAdapter(this, null, false)

        rv_movie.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        supportLoaderManager.initLoader(LOADER_MOVIES, null, this)

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        when(id){
            LOADER_MOVIES ->{
                return CursorLoader(this,
                    queryUri,
                    projection,
                    selectionClause,
                    selectionArgs,
                    sortOrder)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
       when(loader.id){
           LOADER_MOVIES -> adapter.swapCursor(data)
       }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapter.swapCursor(null)
    }

}
