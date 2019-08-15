package com.example.sinoyd.frameapplication.KotlinFrame.Service

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.example.sinoyd.frameapplication.KotlinFrame.Mode.DogFoodMessage
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * 作者： scj
 * 创建时间： 2018/7/11
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.Service
 */
class WatchDogService : Service() {
    var timer: CountDownTimer? = null

    //设置倒计时多少秒   5秒
    val millisInFuture: Long = 10 * 1000

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        // 注册订阅者
        EventBus.getDefault().register(this)
        Log.i("scj", "看门狗到位：服务创建完成onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("scj", "看门狗到位：服务创建完成onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("scj", "看门狗走了：服务创建完成onDestroy")
        // 注销订阅者
        EventBus.getDefault().unregister(this)
        //关闭倒计时
        stoptimer()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: DogFoodMessage) {
        Log.i("scj", "狗子吃到了" + event.message)
        //关闭之前的计时器
        stoptimer()
        //重新启动倒计时
        Countdown()
    }


    fun Countdown() {
        timer = object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("scj", "时间在流食$millisUntilFinished")
            }

            override fun onFinish() {
                Log.i("scj", "狗粮吃完了，我要搞事情")
                //发广播，起界面
                val intent = Intent()
                intent.action = "com.start.expression" //对应广播接收者注册的动作
                sendBroadcast(intent)

            }
        }
        timer!!.start()
    }


    fun stoptimer() {
        if (timer != null) {
            timer!!.cancel()
        }
    }


}