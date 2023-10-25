package com.zwb.filemanager.utils

import java.io.File

private const val TAG = "ScanFiles"
fun scanFiles(directoryPath: String): MutableList<String> {
//    val directoryPath = Environment.getExternalStorageDirectory().path + "/DCIM/Draw"
    val directory = File(directoryPath)

    if (directory.exists() && directory.isDirectory) {
        val fileList = mutableListOf<String>()

        val files = directory.listFiles()
        if (files != null) {
            for (file in files) {
                if (file.isFile && file.exists()) {
                    fileList.add(file.absolutePath)
                }
            }
        }

        return fileList
    } else {
        throw Exception("指定路径不存在")
    }
}