package com.example.ujianke4dicoding.notifikasi

import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.ujianke4dicoding.R
import java.util.*

class AlarmDailyReminder {
    companion object {
        val REQUEST_CODE: Int = 100
        val NOTIFICATION_CHANNEL_ID = "10001"


        fun setReminder(context: Context) {
            val calendar: Calendar = Calendar.getInstance()

            calendar.timeInMillis = System.currentTimeMillis()
//            calendar.set(Calendar.HOUR_OF_DAY, 16)
//            calendar.set(Calendar.MINUTE, 58)
//            calendar.set(Calendar.SECOND, 59)


            if (calendar.before(calendar)) {
                calendar.add(Calendar.DATE, 1)
            }


            val alarmManager: AlarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val SDK_INT = Build.VERSION.SDK_INT
            if (SDK_INT < Build.VERSION_CODES.KITKAT) {
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis, getPendingIntent(context)
                )

            } else if (SDK_INT > Build.VERSION_CODES.KITKAT && SDK_INT < Build.VERSION_CODES.M) {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    getPendingIntent(context)
                )

            } else if (SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis, getPendingIntent(context)
                )
            }

        }

        fun showNotification(context: Context, title: String, content: String) {
            val ringtone: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            val notificationCompat: NotificationCompat.Builder = NotificationCompat.Builder(context)
            val notifikasi: Notification? = notificationCompat.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(ringtone)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .build()

            val notifikasiManger: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val notificationChannel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "NOTIFICATION_CHANNEL_NAME",
                    importance
                )
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.enableVibration(true)
                notificationChannel.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

                notificationCompat.setChannelId(NOTIFICATION_CHANNEL_ID)
                notifikasiManger.createNotificationChannel(notificationChannel)
            }
            notifikasiManger.notify(REQUEST_CODE, notifikasi)

        }

        fun getPendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, AlarmReceiver::class.java)
            val peddingIntent = PendingIntent.getBroadcast(
                context,
                REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            return peddingIntent
        }

    }


}