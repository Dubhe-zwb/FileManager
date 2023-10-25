package com.zwb.filemanager.room

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.zwb.filemanager.room.dao.DeviceDao
import com.zwb.filemanager.room.database.DeviceDatabase
import com.zwb.filemanager.room.entity.DeviceEntity
import com.zwb.filemanager.model.MainViewModel
import com.zwb.filemanager.utils.selfToast
import com.zwb.filemanager.utils.selfTry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

private const val TAG = "RoomManager_zwb"

class RoomManager(application: Application, private var mainViewModel: MainViewModel) {
    private var db: DeviceDatabase? = null
    private var deviceDao: DeviceDao? = null
    private var scope = CoroutineScope(Dispatchers.IO)

    init {
        val selfTry = selfTry {
            db = Room.databaseBuilder(
                application,
                DeviceDatabase::class.java,
                "file_manager_db"
            ).build()
            deviceDao = db!!.deviceDao()
        }
        if (selfTry) selfToast(application, tag = 3, string = "设备数据初始化错误！")
    }

    fun query() {
        scope.launch {
            val distinctUntilChanged = deviceDao?.getAllDevice()?.distinctUntilChanged()
            distinctUntilChanged?.collect() {
                val empty = it.isEmpty()
                Log.e(TAG, "query: empty->$empty")
            }
        }
    }

    fun insert(name: String) {
        scope.launch {
            deviceDao?.insert(DeviceEntity(name = name))
        }
    }

    fun delete() {

    }

    fun update() {

    }
}