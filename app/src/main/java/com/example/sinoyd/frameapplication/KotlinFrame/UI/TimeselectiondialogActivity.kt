package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sinoyd.frameapplication.KotlinFrame.Dialog.DateSelectDialog
import com.example.sinoyd.frameapplication.R
import com.sinoyd.environmentsz.Kotlin.getToday
import com.sinoyd.environmentsz.Kotlin.getlastweekToday
import kotlinx.android.synthetic.main.activity_timeselectiondialog.*
import org.jetbrains.anko.act
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.util.*

class TimeselectiondialogActivity : AppCompatActivity() {
    var date: Date = Date()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeselectiondialog)
        bt.onClick {
            val dateSelectDialog = DateSelectDialog(act, R.style.Theme_Dialog_Transparent, "yyyy/MM/dd")
            dateSelectDialog.setStartAndEndDate(date.getlastweekToday(), date.getToday())
            dateSelectDialog.setDateSelectListener(object : DateSelectDialog.DateSelectListener {
                override fun selectDate(startDate: String, endDate: String) {
                    toast("起始日期$startDate---末尾日期$endDate")
                }
            }).show()
        }
        tvusage.text = """
//新建对象
val dateSelectDialog = DateSelectDialog(activity, R.style.Theme_Dialog_Transparent, "yyyy/MM/dd")
//设置开始时间
dateSelectDialog.setStartAndEndDate(date.getlastweekToday(), date.getToday())
//设置时间选择之后的监听事件
dateSelectDialog.setDateSelectListener(object : DateSelectDialog.DateSelectListener {
override fun selectDate(startDate: String, endDate: String) {
//TODO  拿到时间需要做什么？
}
})
//显示控件
.show()
            """
    }
}
