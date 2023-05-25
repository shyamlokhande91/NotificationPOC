package com.example.notifications.notificationModule.uiModule

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notifications.R
import com.example.notifications.navigation.NotificationScreens
import com.example.notifications.notificationModule.viewModel.NotificationViewModel
import com.example.notifications.utils.AppPermission
import com.example.notifications.utils.CommonUtils

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SendNotification(
    context: Context,
    navController: NavController,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(50.dp)
        ) {

            var notiTitle by remember { mutableStateOf(TextFieldValue("")) }
            var msg by remember { mutableStateOf(TextFieldValue("")) }
            val keyboardController = LocalSoftwareKeyboardController.current

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = notiTitle,
                singleLine = true,
                onValueChange = {
                    notiTitle = it
                },
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(text = stringResource(R.string.txt_hint_notification_title)) },
            )

            Spacer(modifier = Modifier.size(20.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = msg,
                singleLine = true,
                onValueChange = {
                    msg = it
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(text = stringResource(R.string.txt_notification_msg)) },
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(onClick = {
                if (AppPermission().checkNotificationPermission(context as Activity)) {
                    if (notiTitle.text.isEmpty()) {
                        CommonUtils().showToastMsg(context, "Please enter notification title")
                    } else if (msg.text.isEmpty()) {
                        CommonUtils().showToastMsg(context, "Please enter notification msg")
                    } else {
                        MyNotification().fireNotification(
                            context = context, title = notiTitle.text, msg = msg.text
                        )
                        keyboardController?.hide()
                    }
                }
            }) {
                Text(text = stringResource(R.string.txt_send_notification))
            }
            Spacer(modifier = Modifier.size(20.dp))


            Button(onClick = {
                navController.navigate(NotificationScreens.NotificationListScreen.name)
            }) {
                Text(text = stringResource(R.string.txt_show_notification))
            }
        }
    }
}