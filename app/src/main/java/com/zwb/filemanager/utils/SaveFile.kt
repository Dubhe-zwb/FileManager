package com.zwb.filemanager.utils

import android.graphics.Bitmap
import android.util.Log
import java.io.File
import java.io.FileOutputStream

private const val TAG = "SaveFile"

/**
 * 例如：Environment.getExternalStorageDirectory().path + "/DCIM/Draw/${"test_" + System.currentTimeMillis() + ".jpg"}"
 */
fun saveBitmapToFile(bitmap: Bitmap, filePath: String, isCover: Boolean = true): Boolean {
    // 检查 Draw 文件夹是否存在
    val file = File(filePath)
    val parentFile = file.parentFile
    if (parentFile != null) {
        if (!parentFile.exists()) {
            // 文件夹不存在，立即创建
            parentFile.mkdirs()
        }
    } else throw Exception("parentFile==null")

    // 检查 a1.jpg 文件是否存在
    if (file.exists()) {
        // 文件存在，判断是否覆盖
        if (!isCover) {
            // 不覆盖，返回
            return true
        }
        file.delete()
    }

    try {
        Log.e(TAG, "saveBitmapToFile: into")
        // 开始写入文件
        val outputStream = FileOutputStream(file)
        outputStream.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
        }
        return true
    } catch (e: Exception) {
        throw Exception(e.message)
    }
}

fun createDirectoryIfNotExists(path: String) {
    // 获取指定路径的 File 对象
    val file = File(path)

    // 如果文件夹不存在，则创建文件夹
    if (!file.exists() || !file.isDirectory) {
        file.mkdirs()
    }
}

fun deleteFiles(list: List<String>): Boolean {
    try {
        list.forEach {
            val file = File(it)
            if (file.exists() && file.isFile) {
                file.delete()
            }
        }
        return true
    } catch (e: Exception) {
        throw Exception("删除文件出错！")
    }
}