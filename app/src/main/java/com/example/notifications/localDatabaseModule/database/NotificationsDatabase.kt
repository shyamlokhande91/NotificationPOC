package com.example.notifications.localDatabaseModule.database

import android.content.Context
import androidx.room.*
import com.example.notifications.localDatabaseModule.utils.DATABASE_NAME
import com.app.ship2myid.localDatabaseModule.database.dao.NotificationsDao
import com.example.notifications.localDatabaseModule.utils.Convertors
import com.example.notifications.notificationModule.models.Notifications


@Database(entities = [Notifications::class], version = 1)
@TypeConverters(Convertors::class)
abstract class NotificationsDatabase : RoomDatabase() {
    abstract fun notificationDatabaseDao(): NotificationsDao

    companion object {
        @Volatile
        private var INSTANCE: NotificationsDatabase? = null

        fun getDatabase(context: Context): NotificationsDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, NotificationsDatabase::class.java, DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }

            }
            return INSTANCE!!
        }
    }
}