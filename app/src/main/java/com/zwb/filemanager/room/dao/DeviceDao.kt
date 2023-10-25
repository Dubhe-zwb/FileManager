package com.zwb.filemanager.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.zwb.filemanager.room.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg deviceEntity: DeviceEntity)

    @Update
    fun update(vararg deviceEntity: DeviceEntity)

    @Delete
    fun delete(vararg deviceEntity: DeviceEntity)

    /**
     * 注意：Room 中的可观察查询有一项重要限制 - 只要对表中的任何行进行更新（无论该行是否在结果集中），查询就会重新运行。
     * 通过应用相应库中的 distinctUntilChanged() 运算符：流、RxJava 或 LiveData，可以确保仅在实际查询结果发生更改时通知界面。
     * 例:   userDao.queryAllUsers().distinctUntilChanged()
     */

    @Query("DELETE FROM DeviceEntity") // 表名会自动转大写
    fun deleteAll()

    @Query("SELECT name FROM DeviceEntity")
    fun getAllDevice(): Flow<List<String>>
}
