package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import com.example.sinoyd.frameapplication.KotlinFrame.Adapter.Todoadaptersimple
import com.example.sinoyd.frameapplication.KotlinFrame.BroadcastReceive.AlarmReceiver
import com.example.sinoyd.frameapplication.KotlinFrame.Dialog.DateTimePickerControl
import com.example.sinoyd.frameapplication.KotlinFrame.Service.WatchDogService
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.activity_alarm.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*


class AlarmActivity : AppCompatActivity() {

    var list: ArrayList<String> = ArrayList()
    var adapter: Todoadaptersimple? = null
    var date: Date = Date()
    var n = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        //设置闹铃
        setalarm.onClick {
            var datedialog = DateTimePickerControl(this, android.app.AlertDialog.THEME_HOLO_LIGHT, "datetime", "#0f82ff")
            datedialog.SetDateSelectListener {
                list.add(it)
                setalarmdate(it)
                adapter = Todoadaptersimple(list)
                lv.adapter = adapter
            }
            datedialog.show()

        }

        //关闭
        btgou.onClick {
            var serviceintent = Intent(this, WatchDogService::class.java)
            this.stopService(serviceintent)
        }
    }

    private fun setalarmdate(it: String) {
        // 进行闹铃注册
        val intent = Intent(this@AlarmActivity, AlarmReceiver::class.java)
        val sender = PendingIntent.getBroadcast(this@AlarmActivity, n++, intent, 0)

        var firstTime = SystemClock.elapsedRealtime()
        val systemTime = System.currentTimeMillis()
        val c = Calendar.getInstance()
        c.time = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(it)
        // 选择的定时时间
        val selectTime = c.timeInMillis
        if (systemTime > selectTime) {
            toast("设置的时间小于当前时间")
            return
        }
        // 计算现在时间到设定时间的时间差
        val time = selectTime - systemTime
        firstTime += time
        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //每隔多少时间闹铃   1000*60  闹铃
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 1000 * 60, sender)
        toast("设置重复闹铃成功")
    }
}
