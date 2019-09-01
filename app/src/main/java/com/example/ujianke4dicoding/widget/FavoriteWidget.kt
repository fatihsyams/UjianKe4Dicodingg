package com.example.ujianke4dicoding.widget;

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import android.widget.Toast
import com.example.ujianke4dicoding.R


class FavoriteWidget : AppWidgetProvider() {
    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }

    }

    companion object {

        val EXTRA_ITEM = "EXTRAITEM"
        val TOAST_ACTION = "TOASTACTION"

        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val intent = Intent(context, ServiceWidget::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)))
            val view = RemoteViews(context.opPackageName, R.layout.movie_benner)
            view.setRemoteAdapter(R.id.stack_view, intent)
            view.setEmptyView(R.id.stack_view, R.id.empty_view)
            val toast = Intent(context, FavoriteWidget::class.java)
            toast.setAction(TOAST_ACTION)
            toast.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            toast.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)))

            val pendingIntent =
                PendingIntent.getBroadcast(context, 0, toast, PendingIntent.FLAG_UPDATE_CURRENT)
            view.setPendingIntentTemplate(R.id.stack_view, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, view)

        }
    }


    override fun onReceive(context: Context?, intent: Intent?) {


        if (intent?.action.equals(TOAST_ACTION)) {
            val title = intent?.getStringExtra(EXTRA_ITEM)
            Toast.makeText(context, "Favorite Movie $title", Toast.LENGTH_SHORT).show()
        }
        super.onReceive(context, intent)

    }
}