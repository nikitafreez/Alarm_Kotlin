package com.example.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MyAlarmReciver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        var mediaPlayer = MediaPlayer.create(p0, R.raw.music)
        mediaPlayer.start()
        Toast.makeText(p0!!, "ПРОСНИСЬ", Toast.LENGTH_LONG).show()
        Thread.sleep(15000)
        mediaPlayer.stop()
    }
}