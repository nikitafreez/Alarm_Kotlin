package com.example.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_timer.setOnClickListener {
            setAlarm()
        }
    }

    private fun setAlarm() {
        //Задаём текущую дату
        val curHour = SimpleDateFormat("HH")
        val curMinute = SimpleDateFormat("mm")
        val curSecond = SimpleDateFormat("ss")

        val currentHour = curHour.format(Date()).toString()
        val currentMinute = curMinute.format(Date()).toString()
        val currentSeconds = curSecond.format(Date()).toString()
        // Время на таймере
        val hours = timer_clock.hour
        val minutes = timer_clock.minute
        val seconds = 0;
        //Получение разницы времени
        val techHour = hours - currentHour.toInt()
        val techMinutes = minutes - currentMinute.toInt()
        val techSeconds = seconds - currentSeconds.toInt()
        val sec = ((techHour * 60) * 60) + (techMinutes * 60) + techSeconds

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(applicationContext, MyAlarmReciver::class.java)
        var pi = PendingIntent.getBroadcast(applicationContext, 111, i, 0)

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() +(sec * 1000), pi)
        Toast.makeText(this, "Будильник поставлен на $sec секунд", Toast.LENGTH_LONG).show()
    }
}