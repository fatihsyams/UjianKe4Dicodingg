package com.example.ujianke4dicoding

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ujianke4dicoding.screen.favorite.FavoriteFragment
import com.example.ujianke4dicoding.screen.movie.MoviesFragment
import com.example.ujianke4dicoding.screen.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var prevMenuItem: MenuItem?= null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(TvShowFragment(), 0)
            }
            R.id.navigation_dashboard -> {
                switchFragment(MoviesFragment(), 1)
            }
            R.id.navigation_notifications -> {
                switchFragment(FavoriteFragment(), 2)
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

    private fun changeMenuStatus(position:Int){
        if (prevMenuItem != null){
            prevMenuItem?.isChecked = false
        }else{
            nav_view.menu.getItem(0).isChecked = true
        }

        nav_view.menu.getItem(position).isChecked = true
        prevMenuItem = nav_view.menu.getItem(position)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.gantibahasa, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item_change -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
