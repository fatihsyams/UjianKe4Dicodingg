package com.example.ujianke4dicoding

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.ujianke4dicoding.screen.ReminderActivity
import com.example.ujianke4dicoding.screen.favorite.FavoriteFragment
import com.example.ujianke4dicoding.screen.movie.MoviesFragment
import com.example.ujianke4dicoding.screen.movie.MoviesViewModel
import com.example.ujianke4dicoding.screen.tvshow.TvShowFragment
import com.example.ujianke4dicoding.screen.tvshow.TvShowViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private var prevMenuItem: MenuItem? = null

    private val viewmodel: MoviesViewModel by lazy {
        ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    private val viewModelTv: TvShowViewModel by lazy {
        ViewModelProviders.of(this).get(TvShowViewModel::class.java)
    }


    var isMovieFragment: Boolean = false

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    switchFragment(TvShowFragment(), 0)
                    isMovieFragment = false

                }
                R.id.navigation_dashboard -> {
                    switchFragment(MoviesFragment(), 1)
                    isMovieFragment = true
                    /*searchView.visibility = View.VISIBLE*/
                }
                R.id.navigation_notifications -> {
                    switchFragment(FavoriteFragment(), 2)
//                searchView.visibility = View.GONE
                }
            }
            false
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        switchFragment(TvShowFragment(), 0)
    }

    private fun switchFragment(fragment: Fragment, position: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
        changeMenuStatus(position)
    }

    private fun changeMenuStatus(position: Int) {
        if (prevMenuItem != null) {
            prevMenuItem?.isChecked = false
        } else {
            nav_view.menu.getItem(0).isChecked = true
        }

        nav_view.menu.getItem(position).isChecked = true
        prevMenuItem = nav_view.menu.getItem(position)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.gantibahasa, menu)
        menuInflater.inflate(R.menu.menu_reminder, menu)
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.search_menu)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = "Search View Hint"
        searchView.setOnQueryTextListener(this)


        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item_change -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }
        }
        when (item?.itemId) {
            R.id.menu_reminder -> {
                val intent = Intent(this, ReminderActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if (isMovieFragment) {
            switchFragment(MoviesFragment.newInstance(""), 1)

        } else {
            switchFragment(newText?.let { TvShowFragment.newInstance(it) }!!, 0)
        }
        return true
    }
}

