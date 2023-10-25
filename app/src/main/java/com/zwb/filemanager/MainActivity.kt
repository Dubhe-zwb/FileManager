package com.zwb.filemanager

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.zwb.filemanager.model.MainViewModel
import com.zwb.filemanager.ui.navigation.PageNavigation
import com.zwb.filemanager.ui.theme.FileManagerTheme
import com.zwb.filemanager.utils.PermissionToFileManage.permissionToFileManage
import kotlinx.coroutines.launch

private const val TAG = "MainActivity_zwb"

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(
            this, SavedStateViewModelFactory(application, this)
        )[MainViewModel::class.java]

        // 观察 StateFlow 对象来接收数据的更新
        lifecycleScope.launch {
            mainViewModel.permissionStates.collect() {
                val isStartPermission = it.isStartPermission
                val permission = it.permission
                Log.e(
                    TAG,
                    "onCreate: isStartPermission->$isStartPermission---permission->$permission"
                )
                if (isStartPermission && "" != permission)
                    permissionToFileManage(this@MainActivity, permission)
            }
        }

        setContent {
            FileManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageNavigation(mainViewModel = mainViewModel)
                }
            }
        }
    }

    fun updatePermissionResult(result: Boolean) {
        mainViewModel.updatePermissionResult(result)
    }
}



