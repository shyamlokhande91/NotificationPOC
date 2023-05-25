package com.example.notifications.notificationModule.uiModule

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notifications.localDatabaseModule.database.NotificationsDatabase
import com.example.notifications.R
import com.example.notifications.notificationModule.models.Notifications

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    //showNotifications()
}

@Composable
fun ShowNotifications(navController: NavController, context: Context) {
    if (getNotificationList(context).size > 0) {
        BindNotifications(context)
    } else {
        NoNotificationsAvailable()
    }
}

@Composable
private fun BindNotifications(context: Context) {

    Column() {

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = stringResource(R.string.txt_title_notifications),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(10.dp))

        LazyColumn(content = {
            items(getNotificationList(context)) { item ->
                displayList(title = item.title, subtitle = item.msg)
            }
        })
    }

}


@Composable
fun NoNotificationsAvailable() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.txt_no_notifications_available),
                color = colorResource(id = R.color.purple_200)
            )

        }
    }
}

@Composable
fun displayList(
    img: Int = R.drawable.ic_noti, title: String = "", subtitle: String = ""
) {

    Card(
        elevation = 20.dp, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .weight(.1f),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.purple_200))
            )

            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .weight(.9f)
            ) {

                Text(
                    text = title, style = MaterialTheme.typography.h6
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Thin,
                )
            }


        }
    }
}


fun getNotificationList(context: Context): MutableList<Notifications> {
    val notificationsDatabase = NotificationsDatabase.getDatabase(context)
    val notificationList =
        notificationsDatabase?.notificationDatabaseDao()?.getNotificationsList()!!
    return notificationList!!
}



