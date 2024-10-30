package com.example.datetimenotificationapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReciever: BroadcastReceiver (){
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("trace", "received...")
        sendNotification(
            title = intent!!.getStringExtra("title")!!,
            intent.getStringExtra("text")!!,
            context = context!!
        )
    }

}