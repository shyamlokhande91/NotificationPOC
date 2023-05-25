package com.app.ship2myid.localDatabaseModule.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notifications.notificationModule.models.Notifications

@Dao
interface NotificationsDao {

    @Insert
    suspend fun insertNotification(notifications: Notifications)

/*    @Query("Select * from notifications")
    fun getNotificationsList(): LiveData<List<Notifications>>*/

    @Query("Select * from notifications")
    fun getNotificationsList(): MutableList<Notifications>
}