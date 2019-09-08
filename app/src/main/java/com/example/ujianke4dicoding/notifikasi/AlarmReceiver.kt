package com.example.ujianke4dicoding.notifikasi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.let { AlarmDailyReminder.showNotification(it, "Let's open Catalogue Movie", "We have many Movies" ) }
        Log.d("broadcast", "ada")
    }
}