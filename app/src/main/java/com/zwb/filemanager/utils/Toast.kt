package com.zwb.filemanager.utils

import android.app.Activity
import android.app.Application
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun selfToast(application: Application, tag: Int, string: String) {
    CoroutineScope(Dispatchers.Main).launch {
        when (tag) {
            3 -> Toast.makeText(application, string, Toast.LENGTH_SHORT).show()
        }
    }
}
fun selfToast(application: Activity, tag: Int, string: String) {
    CoroutineScope(Dispatchers.Main).launch {
        when (tag) {
            3 -> Toast.makeText(application, string, Toast.LENGTH_SHORT).show()
        }
    }
}