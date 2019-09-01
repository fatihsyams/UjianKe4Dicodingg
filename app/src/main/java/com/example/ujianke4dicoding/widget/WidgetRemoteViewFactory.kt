package com.example.ujianke4dicoding.widget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.HttpException
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.target.Target
import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.database.AppDatabase
import com.example.ujianke4dicoding.response.ResultsItem
import java.util.concurrent.ExecutionException

class WidgetRemoteViewFactory(val context: Context, intent: Intent?) :
    RemoteViewsService.RemoteViewsFactory {

    private var items: List<ResultsItem> = listOf()


    override fun onCreate() {

    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun onDataSetChanged() {
        val db = AppDatabase
        items = db.getDB(context).movieDao().getAll()


    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getViewAt(p0: Int): RemoteViews {
        val rmtView = RemoteViews(context.packageName, R.layout.favorite_widget)
        with(rmtView) {
            setTextViewText(R.id.tv_title, items.get(p0).title)

            try {
                val bm = Glide.with(context)
                    .asBitmap()
                    .load("https://image.tmdb.org/t/p/w342/" + items.get(p0).posterPath)
                    .apply(RequestOptions().fitCenter()).submit().get()
                rmtView.setImageViewBitmap(R.id.imgMovies, bm)

            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        val extras = Bundle()
        extras.putString(FavoriteWidget.EXTRA_ITEM, items.get(p0).title)
        val intent = Intent()
        intent.putExtras(extras)
        rmtView.setOnClickFillInIntent(R.id.imgMovies, intent)
        return rmtView

    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun onDestroy() {

    }

}