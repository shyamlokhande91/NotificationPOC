package com.example.notifications.navigation

enum class NotificationScreens {
    SendNotifiactionScreen,
    NotificationListScreen;

    companion object {
        fun fromRoute(route: String): NotificationScreens = when (route.substringBefore("/")) {
            SendNotifiactionScreen.name -> SendNotifiactionScreen
            NotificationListScreen.name -> NotificationListScreen
            null -> SendNotifiactionScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}