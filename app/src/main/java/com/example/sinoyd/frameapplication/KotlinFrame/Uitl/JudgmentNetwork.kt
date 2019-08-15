package com.example.sinoyd.frameapplication.KotlinFrame.Frame.Uitl

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

/**
 * 作者： scj
 * 创建时间： 2018/5/8
 * 版权： 江苏远大信息股份有限公司
 * 描述： 判断网络是否通畅
 */

fun JudgNetwork(con: Context): Boolean {
    val connManager = con
            .getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connManager.activeNetworkInfo
    return networkInfo?.isConnected ?: false
}