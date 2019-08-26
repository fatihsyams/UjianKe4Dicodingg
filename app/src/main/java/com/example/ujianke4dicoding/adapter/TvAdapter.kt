package com.example.ujianke4dicoding.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.response.responsetv.ResultsItemss
import com.example.ujianke4dicoding.screen.detail.detailtv.TvDetailActivity
import kotlinx.android.synthetic.main.item_row.view.*

class TvAdapter(val context: Context, val item: List<ResultsItemss>) :
    RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (item != null) {
            return item.size
        } else {
            return 0
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, TvDetailActivity::class.java)
            intent.putExtra("extra_item", item[position])
            context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt = view.tvMovies
        val img = view.imgMovies

        fun bindItem(item: ResultsItemss) {
            txt.text = item.name
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w342/" + item.posterPath)
                .into(img)
        }
    }

}