package com.example.notifications.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AppPermission {

    fun checkNotificationPermission(context: Activity): Boolean {
        val permission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101
            )
            return false
        }
        return true
    }
}