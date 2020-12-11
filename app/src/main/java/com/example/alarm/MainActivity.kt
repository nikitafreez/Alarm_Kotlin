package com.example.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            setAlarm(5)
        }
    }

    private fun setAlarm(number: Int) {
        //Задаём формат даты (ЧЧ:ММ:СС)
        val date = SimpleDateFormat("HH:mm:ss")
        val text_timer = StringBuilder()
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //Получаем текущую дату
        val now = Calendar.getInstance()
        val calendarList = ArrayList<Calendar>()
        //Создаём список будильников
        for(i in 1..number) calendarList.add(now)
        for(calendar in calendarList) {
            //Ставим будильник на 10 секунд
            calendar.add(Calendar.SECOND, 10)
            val requestCode = Random().nextInt()
            val intent = Intent(this, MyAlarmReciver::class.java)
            intent.putExtra("REQUEST_CODE", requestCode)
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
            val pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            else
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            text_timer.append(date.format(calendar.timeInMillis)).append("\n")
        }
        txt_timer.text = text_timer
        Toast.makeText(this, "Будильник включён", Toast.LENGTH_LONG).show()
    }


}