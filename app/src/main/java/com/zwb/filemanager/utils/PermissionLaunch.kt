package com.zwb.filemanager.utils

import android.app.Activity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.zwb.filemanager.MainActivity

private const val TAG = "PermissionLaunch_zwb"

data object PermissionToFileManage {
    fun permissionToFileManage(context: Activity, permission: String) {
        // 判断一个或多个权限是否全部授予了
        if (checkPermissionStatus(context, permission)) {
            permissionResult(context,true)
            return
        }

        XXPermissions.with(context)
            // 申请单个权限
            .permission(permission)
//            // 申请多个权限
//        .permission(listOf(Permission.CAMERA, Permission.MANAGE_EXTERNAL_STORAGE))
            // 设置权限请求拦截器（局部设置）
            //.interceptor(new PermissionInterceptor())
            // 设置不触发错误检测机制（局部设置）
            //.unchecked()
            .request(object : OnPermissionCallback {

                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        selfToast(context, 3, string = "获取部分权限成功，但部分权限未正常授予")
                        permissionResult(context, true)
                        return
                    }
//                    selfToast(context, 3, string = "获取权限成功.")
                    permissionResult(context, true)
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    if (doNotAskAgain) {
                        selfToast(context, 3, string = "被永久拒绝授权，请手动授予权限")
                        permissionResult(context, false)
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(context, permissions)
                    } else {
                        permissionResult(context, false)
                        selfToast(context, 3, string = "获取权限失败.")
                    }
                }
            })
    }

    fun permissionResult(context: Activity, result: Boolean) {
        when (context) {
            is MainActivity -> context.updatePermissionResult(result)
            else -> selfToast(context, 3, string = "获取授权结果失败,未能更新授权状态！")
        }
    }

    private fun checkPermissionStatus(context: Activity, permission: String): Boolean {
        // 判断一个或多个权限是否全部授予了
        return XXPermissions.isGranted(context, permission)
    }
}




