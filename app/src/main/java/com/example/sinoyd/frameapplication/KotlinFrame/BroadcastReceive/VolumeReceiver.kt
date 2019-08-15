package com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager


/**
 * 作者： scj
 * 创建时间： 2018/7/4
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive
 *
 * 接收系统音量变化的一个广播
 */


class VolumeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        var am = context!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumeListener.onListener(currentVolume)

    }


    //设置监听回调，用于把电量的信息传递给activity
    lateinit var volumeListener: VolumeListener


    interface VolumeListener {
        fun onListener(volume: Int)
    }

    fun setMyListener(energyListener: VolumeListener) {
        this.volumeListener = energyListener
    }

}