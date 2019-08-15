package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive.EnergyReceiver
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.activity_info.*


class InfoActivity : AppCompatActivity(), EnergyReceiver.EnergyListener {
    override fun onListener(level: String) {
        tv1.setpower(level!!)
    }


    var enReceiver: EnergyReceiver = EnergyReceiver()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)


        //广播常量
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        //新建广播
        enReceiver = EnergyReceiver()
        //注册广播
        registerReceiver(enReceiver, intentFilter)
        enReceiver.setMyListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(enReceiver)
    }
}
