package com.zwb.filemanager.utils.server

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

private const val TAG = "Base64_zwb"

@OptIn(ExperimentalEncodingApi::class)
fun base64ToBitmap(data: String): Bitmap {
    val gson = Gson()
    val user = gson.fromJson(data, Result::class.java)
    val image = user.image
    // 解码 base64 编码的数据
    val byteArray = Base64.decode(image)

    // 创建 Bitmap 对象
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

@OptIn(ExperimentalEncodingApi::class)
fun bitmapTobase64(imagePath: String): String {

    val file = HttpClient.getFile(imagePath)
    // 使用 Base64 编码将 Bitmap 对象编码成 base64 字符串
    return Base64.encode(file, 0, file.size)
}