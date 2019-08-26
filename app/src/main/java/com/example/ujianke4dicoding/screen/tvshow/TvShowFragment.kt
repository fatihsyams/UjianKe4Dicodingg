package com.example.ujianke4dicoding.screen.tvshow


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujianke4dicoding.Load

import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.adapter.MultiAdapter
import com.example.ujianke4dicoding.adapter.TvAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    private val viewmodel : TvShowViewModel  by lazy {
            ViewModelProviders.of(this).get(TvShowViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.getDataTv()

        viewmodel.tvLoad.observe(this, Observer {
            pb_tv.visibility = if (it is Load.Loading) View.VISIBLE else View.GONE

            if ( it is Load.Success) {
                rec_tv.apply {
                    adapter = TvAdapter(context, it.data.results?.mapNotNull { it } ?: emptyList())
                    layoutManager = LinearLayoutManager(context)
                }
            }
            else (it is Load.Fail)
                Log.d("TAG", " DATA GA DAPAT")

        })
    }

}
