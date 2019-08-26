package com.example.ujianke4dicoding.screen.detail.detailtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.response.ResultsItem
import com.example.ujianke4dicoding.response.responsetv.ResultsItemss
import com.example.ujianke4dicoding.screen.detail.DetailViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.Exception

class TvDetailActivity : AppCompatActivity() {


    private var menuItem: MenuItem? = null
    private lateinit var movie: ResultsItemss

    val detailViewModel by lazy {
        ViewModelProviders.of(this).get(DetailTvViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_detail)
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
                Toast.makeText(this, "${movie.name} berhasil disimpan", Toast.LENGTH_LONG).show()
            } else {
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
                detailViewModel.deleteMovie(movie)
                Toast.makeText(this, "${movie.name} berhasil dihapus", Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setData() {
        movie = intent.getSerializableExtra("extra_item") as ResultsItemss
        tvTitleDetail.text = movie.name
        textView2.text = movie.overview
        if (movie.isFavorite) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
        }

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w342/" + movie.posterPath)
            .into(imgDetail, object: Callback {
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
