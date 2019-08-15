package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sinoyd.frameapplication.KotlinFrame.Mode.DogFoodMessage
import com.example.sinoyd.frameapplication.KotlinFrame.Service.WatchDogService
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.activity_watch_dog.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.onClick


class WatchDogActivity : AppCompatActivity() {
    var serviceintent: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_dog)
        serviceintent = Intent(this, WatchDogService::class.java)
        tv_watchdog.onClick {
            startservice()
        }

        tv_watchdog_to.onClick {
            //喂狗
            EventBus.getDefault().post(DogFoodMessage("狗粮"))
        }


        tv_watchdog_close.onClick {
            stopservice()
        }
    }


    fun startservice() {
        this.startService(serviceintent)
    }

    fun stopservice() {
        this.stopService(serviceintent)
    }


}
