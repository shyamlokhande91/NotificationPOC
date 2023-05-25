package com.example.notifications.notificationModule.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor() : ViewModel() {

    var notificationTitle : MutableState<String> = mutableStateOf("")

}