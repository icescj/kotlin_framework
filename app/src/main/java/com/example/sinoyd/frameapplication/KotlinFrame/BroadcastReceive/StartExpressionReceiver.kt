package com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 作者： scj
 * 创建时间： 2018/7/11
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive
 *
 * //启动表情页面
 */
class StartExpressionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //接收到表情界面到启动广播
        Log.i("scj", "收到看门狗的指示，准备干件大事")

    }

}