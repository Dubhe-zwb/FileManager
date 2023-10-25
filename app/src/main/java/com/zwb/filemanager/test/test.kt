package com.zwb.filemanager.test

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.atan

private const val TAG = "test"
val list = mutableListOf<Int>()
val mutex = Mutex()

suspend fun modifyList(item: Int) {
    mutex.withLock {
        // 在互斥锁的保护下修改List
        list.add(item)
    }
    // 代码块执行完毕后才能执行剩余代码
    println("Item $item added to the list")
}

fun main(): Unit = runBlocking {
    val number = 3.156
    val formattedNumber = String.format("%.1f", number)
    println(Math.toDegrees(atan(1.0))) // 输出 3.2
}

