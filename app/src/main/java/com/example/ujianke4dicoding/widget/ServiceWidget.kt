package com.example.ujianke4dicoding.widget

import android.content.Intent
import android.widget.RemoteViewsService

class ServiceWidget : RemoteViewsService() {
    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory {
        return WidgetRemoteViewFactory(this, p0)
    }


}