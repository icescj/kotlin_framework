package com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive

import android.content.BroadcastReceiver


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Parcelable
import android.util.Log

/**
 * 作者： scj
 * 创建时间： 2018/7/5
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive
 *
 * 实时监测手机网络的变化
 */
private var update = false

class NetworkChangedReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {

        Log.i("scj", "intent.action---" + intent.action)

        if (WifiManager.WIFI_STATE_CHANGED_ACTION == intent.action) {// 监听wifi的打开与关闭，与wifi的连接无关
            val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
            Log.e("scj", "wifiState:$wifiState")
            when (wifiState) {
                WifiManager.WIFI_STATE_DISABLING -> {
                    Log.e("scj", "wifi关闭中状态")
                }
                WifiManager.WIFI_STATE_DISABLED -> {
                    Log.e("scj", "wifi关闭状态")
                }
                WifiManager.WIFI_STATE_ENABLING -> {
                    Log.e("scj", "wifi打开中状态")
                }
                WifiManager.WIFI_STATE_ENABLED -> {
                    Log.e("scj", "wifi打开状态")
                }
                WifiManager.WIFI_STATE_DISABLED -> {
                    Log.e("scj", "wifi未知状态")
                }
            }
        }


        // 监听wifi的连接状态即是否连上了一个有效无线路由
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION == intent.action) {
            val parcelableExtra = intent.getParcelableExtra<Parcelable>(WifiManager.EXTRA_NETWORK_INFO)
            if (null != parcelableExtra) {
                // 获取联网状态的NetWorkInfo对象
                val networkInfo = parcelableExtra as NetworkInfo
                //获取的State对象则代表着连接成功与否等状态
                val state = networkInfo.state
                //判断网络是否已经连接
                val isConnected = state == NetworkInfo.State.CONNECTED
                if (!update && isConnected) {
                    //由于返回广播2次，只能做标识，执行一次
                    update = true
                    Log.e("scj", "网络可用")
                } else {
                    Log.e("scj", "网络不可用")
                }
            }
        }

        //7.0权限不再有这个广播
        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
            //获取联网状态的NetworkInfo对象
            val info = intent.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.state && info.isAvailable) {
                    if (info.type == ConnectivityManager.TYPE_WIFI || info.type == ConnectivityManager.TYPE_MOBILE) {
                        Log.i("scj", getConnectionType(info.type) + "连上")
                    }
                } else {
                    Log.i("scj", getConnectionType(info.type) + "断开")
                }
            }
        }

    }

    private fun getConnectionType(type: Int): String {
        var connType = ""
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "移动网络数据"
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络"
        }
        return connType
    }

}