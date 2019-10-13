package com.evartem.jobajob.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.evartem.jobajob.R
import com.evartem.jobajob.presentation.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    companion object {
        private const val CHANNEL_ID = "JobAJob_main_channel"
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val data = message.data
        Log.d("FA:data", data.toString())

        var messageTitle = "JobAJob News"
        var messageBody = "Check out what's new at JobAJob"

        message.notification?.apply {
            title?.also {
                Log.d("FA:title", it)
                messageTitle = it
            }
            body?.also {
                Log.d("FA:body", it)
                messageBody = it
            }
        }

        createAndPublishNotification(message, messageTitle, messageBody)
    }

    private fun createAndPublishNotification(message: RemoteMessage, title: String, body: String) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_dashboard)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_MAX)


        val notificationIntent = MainActivity.createNotificationIntent(this, message)
        builder.setContentIntent(PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT))

        notificationManager.notify(0, builder.build())
    }
}