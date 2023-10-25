package com.zwb.filemanager.utils

private const val TAG = "SelfTry_zwb"
fun selfTry(block: () -> Unit) :Boolean{
    return try {
        block()
        false
    } catch (e: Exception) {
        selfLog(level = "e", tag = TAG, message = "selfTry", string = e.message.toString())
        true
    }
}