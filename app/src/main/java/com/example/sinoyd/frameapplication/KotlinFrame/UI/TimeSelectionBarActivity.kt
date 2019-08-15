package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sinoyd.frameapplication.KotlinFrame.View.TimeSelectionBarView
import com.example.sinoyd.frameapplication.R
import com.sinoyd.environmentsz.Kotlin.getDateofYesterday
import com.sinoyd.environmentsz.Kotlin.getToday
import kotlinx.android.synthetic.main.activity_time_selection_bar.*
import org.jetbrains.anko.toast
import java.util.*

class TimeSelectionBarActivity : AppCompatActivity() {

    var date: Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_selection_bar)
        timeselectionbar.setdefaulttime(date.getDateofYesterday(), date.getToday())
                .setdefaulttextcolor("#0000ff")
                .setfaultimagecolor("#ff0000")
                .setTimeSelectionbarlistener(object : TimeSelectionBarView.TimeSelectionbarlistener {
                    override fun timeselectionbardoing(lefttime: String, righttime: String) {
                        toast("初始时间$lefttime~结束时间$righttime")
                    }

                })
                .show()


        tvusage.text = """
//设置默认显示
timeselectionbar.setdefaulttime(date.getDateofYesterday(), date.getToday())
//设置默认字体颜色
.setdefaulttextcolor("#0000ff")
//设置默认箭头颜色
.setfaultimagecolor("#ff0000")
//设置我需要干什么监听
.setTimeSelectionbarlistener(
object : TimeSelectionBarView.TimeSelectionbarlistener {
override fun timeselectionbardoing(lefttime: String, righttime: String) {
             //TODO 我拿到2个时间，我要干嘛？
}
})
//显示出来
.show()
        """
    }
}
