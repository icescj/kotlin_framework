package com.example.sinoyd.frameapplication.KotlinFrame.Uitl

import android.content.Context
import android.net.ConnectivityManager


/**
 * 作者： scj
 * 创建时间： 2018/7/5
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.Uitl
 */
object NetworkUtil {
    val TYPE_NONE = -1
    val TYPE_MOBILE = 0
    val TYPE_WIFI = 1
    /**
     * 获取网络状态
     *
     * @param context
     * @return one of TYPE_NONE, TYPE_MOBILE, TYPE_WIFI
     * @permission android.permission.ACCESS_NETWORK_STATE
     */
    fun getNetWorkStates(context: Context): Int {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected) {
            return TYPE_NONE//没网
        }

        val type = activeNetworkInfo.type
        when (type) {
            ConnectivityManager.TYPE_MOBILE -> return TYPE_MOBILE//移动数据
            ConnectivityManager.TYPE_WIFI -> return TYPE_WIFI//WIFI
            else -> {
            }
        }
        return TYPE_NONE
    }

}