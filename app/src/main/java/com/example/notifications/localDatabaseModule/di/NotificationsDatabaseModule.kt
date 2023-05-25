package com.app.ship2myid.localDatabaseModule.database.di

import android.content.Context
import androidx.room.Room
import com.example.notifications.localDatabaseModule.utils.DATABASE_NAME
import com.example.notifications.localDatabaseModule.database.NotificationsDatabase
import com.app.ship2myid.localDatabaseModule.database.dao.NotificationsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NotificationsDatabaseModule {

    @Provides
    @Singleton
    fun provideNotificationDatabaseDAO(notificationsDatabase: NotificationsDatabase): NotificationsDao {
        return notificationsDatabase.notificationDatabaseDao()
    }

    fun provideNotificationDatabase(@ApplicationContext context: Context): NotificationsDatabase {
        return Room.databaseBuilder(context, NotificationsDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries().build()
    }
}