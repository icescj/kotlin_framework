package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.content.Context
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.SeekBar
import com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive.VolumeReceiver
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.activity_touch.*


class TouchActivity : AppCompatActivity(), VolumeReceiver.VolumeListener {
    override fun onListener(volume: Int) {
        //音量回调
        Log.i("scj广播到的音量", "$volume")
        seekBar.progress = volume
    }

    var am: AudioManager? = null
    var volumeReceiver: VolumeReceiver = VolumeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)

        //注册音量广播
        RegisterReceiver()
        //监听事件
        setlisteners()


        //获取音量管理类
        am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //获取系统最大音量
        val maxVolume = am!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        seekBar.max = maxVolume
        Log.i("scj最大音量", "$maxVolume")
        //获取当前音量
        val currentVolume = am!!.getStreamVolume(AudioManager.STREAM_MUSIC)
        seekBar.progress = currentVolume
        Log.i("scj当前音量", "$currentVolume")


    }

    //注册音量广播
    private fun RegisterReceiver() {
        //广播常量
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION")
        volumeReceiver = VolumeReceiver()
        //注册广播
        registerReceiver(volumeReceiver, intentFilter)
        volumeReceiver.setMyListener(this)

    }

    //设置监听
    private fun setlisteners() {
        //seebar的监听事件
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    //设置系统音量
                    am!!.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
                    val currentVolume = am!!.getStreamVolume(AudioManager.STREAM_MUSIC)
                    seekBar.progress = currentVolume
                    Log.i("scj当前音量", "$currentVolume")
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    //销毁广播
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(volumeReceiver)
    }


//    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
//        if (event.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//            am!!.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0)
//        } else if (event.keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//            am!!.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0)
//        }
//        return false
//    }
}
