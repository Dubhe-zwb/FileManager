package com.zwb.filemanager.utils

import java.util.concurrent.locks.ReentrantLock

fun <T> ReentrantLock.withLock(action: () -> T): T {
    lock()
    try {
        return action()
    }  finally {
        unlock()
    }
}