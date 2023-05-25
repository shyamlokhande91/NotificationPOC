package com.example.notifications.notificationModule.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notifications")
data class Notifications(
    @PrimaryKey(autoGenerate = true)
    val notificationId: Int,
    val title: String,
    val msg: String,
    val icon: String,
    val date : Date
)
