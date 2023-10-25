package com.zwb.filemanager.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Fts4                           //应用需要通过全文搜索 (FTS) 快速访问数据库信息
@Entity
data class DeviceEntity(
//    /* 为FTS表支持的实体指定主键是可选的，但是如果指定了，则必须使用Int类型和rowid列名 */
//    @PrimaryKey @ColumnInfo(name = "rowid") val id: Int,

    @PrimaryKey @ColumnInfo(name = "name") val name: String,
)
