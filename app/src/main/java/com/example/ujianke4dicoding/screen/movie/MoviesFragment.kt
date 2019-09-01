package com.example.ujianke4dicoding.screen.movie


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujianke4dicoding.Load
import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.adapter.MultiAdapter
import com.example.ujianke4dicoding.response.ResultsItem
import com.example.ujianke4dicoding.response.responsetv.ResultsItemss
import com.example.ujianke4dicoding.screen.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {

    val item: List<ResultsItem> = arrayListOf()

    companion object {
        fun newInstance(query: String = ""): MoviesFragment {
            val fragment = MoviesFragment()
            val bundle = Bundle()
            bundle.putString("keyquery", query)
            fragment.arguments = bundle
            return fragment
        }

        var query: String? = null
        var tvShow: List<ResultsItem> = listOf()
    }


    private val viewmodel: MoviesViewModel by lazy {
        ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.getDataMovies()

        query = arguments?.getString("keyquery")



        viewmodel.moviesLoad.observe(this, Observer {
            pb_movies.visibility = if (it is Load.Loading) View.VISIBLE else View.GONE
            if (it is Load.Success) {

                if (query != null) {
                    tvShow = it.data.results!!.filter { it.title!!.contains(query!!) }
                } else if (query.equals("")) {
                    tvShow = it.data.results!!
                } else {
                    tvShow = it.data.results!!
                }

                rec_movies.adapter =
                    MultiAdapter(context!!, it.data.results?.mapNotNull { it } ?: emptyList())
                rec_movies.layoutManager = LinearLayoutManager(context)

            } else (it is Load.Fail)
            Log.d("TAG", " DATA GA DaPAT")
        })
    }


}
