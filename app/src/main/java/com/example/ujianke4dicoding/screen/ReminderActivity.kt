package com.example.ujianke4dicoding.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.example.ujianke4dicoding.R
import com.example.ujianke4dicoding.notifikasi.AlarmDailyReminder
import com.example.ujianke4dicoding.notifikasi.AlarmReceiver
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        swtReminder.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if (p1) {
                    AlarmDailyReminder.setReminder(this@ReminderActivity)
                }
            }

        })

    }
}
