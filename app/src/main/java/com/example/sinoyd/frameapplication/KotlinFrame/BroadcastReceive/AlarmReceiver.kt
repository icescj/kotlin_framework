package com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.jetbrains.anko.toast
import java.util.*

/**
 * 作者： scj
 * 创建时间： 2018/7/5
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive
 *
 *
 * 闹铃广播
 */
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("scj", "executed at " + Date().toString() + "闹铃响了, 可以做点事情了~~")
        context!!.toast("闹铃响了, 可以做点事情了~~")
    }

}
