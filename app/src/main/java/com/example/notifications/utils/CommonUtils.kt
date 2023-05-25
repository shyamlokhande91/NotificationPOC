package com.example.notifications.utils

import android.content.Context
import android.widget.Toast

class CommonUtils {
    fun showToastMsg(context: Context, msg: String) {
        Toast.makeText(
            context, msg, Toast.LENGTH_SHORT
        ).show()
    }
}
