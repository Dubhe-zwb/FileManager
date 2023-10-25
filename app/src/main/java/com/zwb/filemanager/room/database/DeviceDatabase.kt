package com.zwb.filemanager.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zwb.filemanager.room.dao.DeviceDao
import com.zwb.filemanager.room.entity.DeviceEntity

/**
 * 用法：
 *        val db = Room.databaseBuilder(pplicationContext,AppDatabase::class.java, "database-name").build()
 *        val userDao = db.userDao()
 *        val users: List<User> = userDao.getAll()
 */

@Database(
    entities = [DeviceEntity::class],
    version = 1,
    exportSchema = false /* 是否导出数据库的模式信息*/
)
abstract class DeviceDatabase : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao
}