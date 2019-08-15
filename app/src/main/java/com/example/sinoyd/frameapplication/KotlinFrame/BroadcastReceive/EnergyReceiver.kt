package com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 作者： scj
 * 创建时间： 2018/6/29
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive
 *
 *
 * 接收系统电量变化的一个广播
 */
class EnergyReceiver : BroadcastReceiver() {

    //设置监听回调，用于把电量的信息传递给activity
    lateinit var energyListener: EnergyListener

    override fun onReceive(context: Context, intent: Intent) {
        //获取当前电量
        val level = intent.getIntExtra("level", 0)
        //总电量
        val scale = intent.getIntExtra("scale", 100)
        //当前电量
        val power = level * 100 / scale
        energyListener.onListener(power.toString() + "")
        Log.i("scj电量", power.toString() + "%")
    }


    interface EnergyListener {
        fun onListener(level: String)
    }

    fun setMyListener(energyListener: EnergyListener) {
        this.energyListener = energyListener
    }
}
