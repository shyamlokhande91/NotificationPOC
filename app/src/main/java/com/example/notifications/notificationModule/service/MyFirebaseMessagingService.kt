package com.example.notifications.notificationModule.service

import android.util.Log
import com.example.notifications.notificationModule.uiModule.MyNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("Notification Token ", token)

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("NotificationPOC", "From: ${remoteMessage.from}")
        if (remoteMessage.notification != null) {
            MyNotification().fireNotification(
                this.applicationContext,
                remoteMessage.notification!!.title!!,
                remoteMessage.notification!!.body!!
            )
        }
    }
}