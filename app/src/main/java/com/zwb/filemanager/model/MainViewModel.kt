package com.zwb.filemanager.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.zwb.filemanager.room.RoomManager
import com.zwb.filemanager.utils.States
import com.zwb.filemanager.utils.selfLog
import com.zwb.filemanager.utils.selfToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File

private const val TAG = "MainViewModel_zwb"

class MainViewModel(
    private var application: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {
    val uiStates = savedStateHandle.getStateFlow("uiStates", States.UiStates())
    val permissionStates =
        savedStateHandle.getStateFlow("permissionStates", States.PermissionStates())

    private var roomManager: RoomManager? = null

    private var scope = CoroutineScope(Dispatchers.Default)


    init {
        roomManager = RoomManager(application, this)

        initExternalPrivateFileDir()
    }
    private fun initExternalPrivateFileDir() {
        try {
            val directory = File(application.getExternalFilesDir(null)?.canonicalPath, "FileManager")
            if (!directory.exists()) directory.mkdir()
        } catch (e: Exception) {
            selfLog(
                level = "e",
                tag = TAG,
                message = "initInternalFileDir",
                string = "文件缓存目录创建失败！"
            )
            selfToast(application, 3, "文件缓存目录创建失败！")
        }
    }


    fun updatePermissionResult(permissionResult: Boolean) {
        savedStateHandle["permissionStates"] = permissionStates.value.copy(
            permissionResult = permissionResult
        )
    }

    fun resetPermissionArgument() {
        savedStateHandle["permissionStates"] = permissionStates.value.copy(
            isStartPermission = false,
            permissionResult = false,
            permission = ""
        )
    }

    fun startPermission(isStartPermission: Boolean, permission: String) {
        savedStateHandle["permissionStates"] = permissionStates.value.copy(
            isStartPermission = isStartPermission,
            permission = permission
        )
    }

    override fun onCleared() {
        super.onCleared()
        roomManager = null
    }
}