package com.zwb.filemanager

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.tencent.bugly.crashreport.CrashReport
import com.zwb.filemanager.utils.selfLog

class FileApplication : MultiDexApplication() {
    private val TAG = "Application_zwb"
    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(applicationContext, "f59d77a8fa", false)

        val versionName: String = BuildConfig.VERSION_NAME
        selfLog(tag = TAG, message = "onCreate", string = "versionName->$versionName")

    }
}