package com.zwb.filemanager.utils


import com.zwb.filemanager.utils.Themes.Companion.SYSTEM_THEME

sealed interface States : java.io.Serializable {
    data class UiStates(
        val pageFlag: Int = 0,                                /*页面选择*/

        val theme: Int = SYSTEM_THEME,
        val dynamicColor: Boolean = true
    ) : States

    data class PermissionStates(
        val isStartPermission: Boolean = false,               /* 是否开始申请权限*/
        val permissionResult: Boolean = false,                /* 申请权限是否成功*/
        val permission: String = "",                          /* 权限内容*/
    ) : States
}

class Modes {
    companion object {
        const val MODE_WIFI: Int = 1
        const val MODE_BLUETOOTH: Int = 2
        const val MODE_USB: Int = 3
    }
}

class Themes {
    companion object {
        const val SYSTEM_THEME: Int = 0
        const val DARK_THEME: Int = 1
        const val LIGHT_THEME: Int = 2
    }
}