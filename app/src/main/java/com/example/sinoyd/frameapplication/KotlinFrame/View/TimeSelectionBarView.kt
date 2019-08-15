package com.example.sinoyd.frameapplication.KotlinFrame.View

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.sinoyd.frameapplication.KotlinFrame.Dialog.DateSelectDialog
import com.example.sinoyd.frameapplication.R
import com.sinoyd.environmentsz.Kotlin.*
import org.jetbrains.anko.toast
import java.util.*

/**
 * 作者： scj
 * 创建时间： 2018/4/10
 * 版权：
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.View
 */

class TimeSelectionBarView(context: Context, attr: AttributeSet) : RelativeLayout(context, attr) {
    var ivleft: ImageView = ImageView(context)
    var ivright: ImageView = ImageView(context)
    var tvleft: TextView = TextView(context)
    var tvright: TextView = TextView(context)
    var tvmiddle: TextView = TextView(context)
    var format: String = "yyyy/MM/dd"
    var date: Date = Date()
    var doinglistener: TimeSelectionbarlistener? = null
    var lefttime: String = ""
    var righttime: String = ""

    //初始化界面信息
    init {
        LayoutInflater.from(context).inflate(R.layout.frame_ui_timeselectionbar_layout, this)
        ivleft = findViewById(R.id.iv_date_arrowLeft)
        tvleft = findViewById(R.id.tv_date_startTime)
        tvmiddle = findViewById(R.id.tv_date_flag)
        ivright = findViewById(R.id.iv_date_arrowRight)
        tvright = findViewById(R.id.tv_date_endTime)
    }

    fun setdefaulttime(time1: String, time2: String, format: String = "yyyy/MM/dd"): TimeSelectionBarView {
        this.format = format
        tvleft.text = time1
        tvright.text = time2
        return this
    }

    fun setdefaulttextcolor(color: String = "#fff"): TimeSelectionBarView {
        tvleft.setTextColor(Color.parseColor(color))
        tvright.setTextColor(Color.parseColor(color))
        return this
    }

    fun setfaultimagecolor(color: String = "#fff"): TimeSelectionBarView {
        ivleft.setColorFilter(Color.parseColor(color))
        ivright.setColorFilter(Color.parseColor(color))
        return this
    }

    fun show() {
        //左边箭头点击事件
        ivleft.setOnClickListener {
            tvleft.text = date.getSpecifiedDayBefore(tvleft.text.toString())
            doinglistener!!.timeselectionbardoing(tvleft.text.toString(), tvright.text.toString())
        }
        //右边箭头点击事件
        ivright.setOnClickListener {
            var temporarytime = date.getSpecifiedDayAfter(tvright.text.toString())
            if (date.isDateOneBigger(temporarytime, date.getToday())) {
                context.toast("日期大于当前日期")
                tvright.text = date.getToday()
            } else {
                tvright.text = temporarytime
            }
            doinglistener!!.timeselectionbardoing(tvleft.text.toString(), tvright.text.toString())
        }
        //中间"至"点击事件
        tvmiddle.setOnClickListener {
            //时间区域选择框
            val dateSelectDialog = DateSelectDialog(context, R.style.Theme_Dialog_Transparent, format)
            dateSelectDialog.setStartAndEndDate(tvleft.text.toString(), tvright.text.toString())
            dateSelectDialog.setDateSelectListener(object : DateSelectDialog.DateSelectListener {
                override fun selectDate(startDate: String, endDate: String) {
                    doinglistener!!.timeselectionbardoing(startDate, endDate)
                }
            }).show()
        }
        tvleft.setOnClickListener { tvmiddle.performClick() }
        tvright.setOnClickListener { tvmiddle.performClick() }
    }

    fun setTimeSelectionbarlistener(lis: TimeSelectionbarlistener): TimeSelectionBarView {
        doinglistener = lis
        return this
    }

    interface TimeSelectionbarlistener {
        fun timeselectionbardoing(lefttime: String, righttime: String)
    }

}