package com.zwb.filemanager.utils

import android.util.Log

fun selfLog(
    isOpen: Boolean = true,
    level: String = "i",
    tag: String = "SelfLog_zwb",
    message: String = "",
    string: String = ""
) {
    if (isOpen)
        when (level) {
            "i" -> Log.i(tag, "$message: $string")
            "e" -> Log.e(tag, "$message: $string")
        }
}