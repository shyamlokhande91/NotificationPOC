package com.example.notifications.notificationModule.uiModule

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.notifications.localDatabaseModule.database.NotificationsDatabase
import com.example.notifications.R
import com.example.notifications.notificationModule.models.Notifications
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class MyNotification {
    val channelId = "FCM100"
    val channelName = "FCMMessage"
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationBuilder: NotificationCompat.Builder

    fun fireNotification(context: Context, title: String, msg: String) {
        val notificationManager =
            context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.description = "My Channel"
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableLights(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        notificationBuilder = NotificationCompat.Builder(context, channelId)
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
        /*notificationBuilder.addAction(
            R.drawable.ic_launcher_background, "Open Message", pendingIntent
        )*/
        notificationBuilder.setContentIntent(
            pendingIntent
        )
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH)
        notificationBuilder.setContentIntent(pendingIntent)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(msg)
        notificationBuilder.setAutoCancel(true)
        notificationManager.notify(100, notificationBuilder.build())


        GlobalScope.launch {
            val notificationsDatabase =
                NotificationsDatabase.getDatabase(context.applicationContext)
            notificationsDatabase.notificationDatabaseDao().insertNotification(
                Notifications(
                    0, title, msg, "", Date()
                )
            )
        }
    }
}