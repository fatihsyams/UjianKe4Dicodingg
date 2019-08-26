package com.example.ujianke4dicoding.screen.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.response.ResultsItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.Exception


class DetailActivity : AppCompatActivity() {

    private var menuItem: MenuItem? = null
    private lateinit var movie: ResultsItem

    val detailViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setData()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        this.menuItem = menu?.findItem(R.id.fav_detail)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.fav_detail) {
            if (!movie.isFavorite) {
                movie.isFavorite = true
                detailViewModel.insertMovie(movie)
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                Toast.makeText(this, "${movie.title} berhasil disimpan", Toast.LENGTH_LONG).show()
            } else {
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
                detailViewModel.deleteMovie(movie)
                Toast.makeText(this, "${movie.title} berhasil dihapus", Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setData() {
        movie = intent.getSerializableExtra("extra_item") as ResultsItem
        tvTitleDetail.text = movie.title
        textView2.text = movie.overview
        if (movie.isFavorite) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
        }

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + movie.posterPath)
                .into(imgDetail, object:Callback{
                    override fun onSuccess() {
                        pb_detail.visibility = View.GONE
                        Log.d("loadPhoto", "success")
                    }

                    override fun onError(e: Exception?) {
                        Log.d("loadPhoto", "failed")
                    }

                })
    }
}
