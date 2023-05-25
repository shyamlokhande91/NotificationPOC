package com.example.notifications.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notifications.notificationModule.uiModule.SendNotification
import com.example.notifications.notificationModule.uiModule.ShowNotifications

@Composable
fun NotificationNavigation(context: Context) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NotificationScreens.SendNotifiactionScreen.name
    ) {

        composable(NotificationScreens.SendNotifiactionScreen.name) {
            SendNotification(context,navController)
        }
        composable(NotificationScreens.NotificationListScreen.name){
            ShowNotifications(navController,context)
        }
    }
}