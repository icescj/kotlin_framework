package com.example.sinoyd.frameapplication.KotlinFrame.View

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.sinoyd.frameapplication.R

/**
 * 作者： scj
 * 创建时间： 2018/6/29
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.View
 * 自定义电量view
 */
class BatteryView(context: Context, attr: AttributeSet) : RelativeLayout(context, attr) {

    var tv20: TextView = TextView(context)
    var tv40: TextView = TextView(context)
    var tv60: TextView = TextView(context)
    var tv80: TextView = TextView(context)
    var tv100: TextView = TextView(context)
    var power: String = "100"


    var bgcolor: Int = Color.parseColor("#e0e0e0")
    var tvcolor: Int = Color.parseColor("#00e676")
    var endtvcolor: Int = Color.parseColor("#FF0000")
    var headcolor: Int = Color.parseColor("#000000")


    init {
        LayoutInflater.from(context).inflate(R.layout.frame_ui_battery, this)
        tv20 = findViewById(R.id.tv_20)
        tv40 = findViewById(R.id.tv_40)
        tv60 = findViewById(R.id.tv_60)
        tv80 = findViewById(R.id.tv_80)
        tv100 = findViewById(R.id.tv_100)
    }
    //设置颜色

    fun setcolor(bgcolor: String, tvcolor: String, endtvcolor: String, headcolor: String): BatteryView {
        this.bgcolor = Color.parseColor(bgcolor)
        this.tvcolor = Color.parseColor(tvcolor)
        this.endtvcolor = Color.parseColor(endtvcolor)
        this.headcolor = Color.parseColor(headcolor)
        return this
    }


    //设置电量
    fun setpower(power: String) {
        this.power = power
        show()
    }

    //显示
    private fun show() {
        when (power.toInt()) {
            in 0..20 -> {
                tv20.visibility = View.VISIBLE
                tv20.setBackgroundColor(endtvcolor)
                tv40.visibility = View.INVISIBLE
                tv60.visibility = View.INVISIBLE
                tv80.visibility = View.INVISIBLE
                tv100.visibility = View.INVISIBLE
            }
            in 20..40 -> {
                tv20.visibility = View.VISIBLE
                tv40.visibility = View.VISIBLE
                tv60.visibility = View.INVISIBLE
                tv80.visibility = View.INVISIBLE
                tv100.visibility = View.INVISIBLE
            }
            in 40..60 -> {
                tv20.visibility = View.VISIBLE
                tv40.visibility = View.VISIBLE
                tv60.visibility = View.VISIBLE
                tv80.visibility = View.INVISIBLE
                tv100.visibility = View.INVISIBLE
            }
            in 60..80 -> {
                tv20.visibility = View.VISIBLE
                tv40.visibility = View.VISIBLE
                tv60.visibility = View.VISIBLE
                tv80.visibility = View.VISIBLE
                tv100.visibility = View.INVISIBLE
            }
            in 80..100 -> {
                tv20.visibility = View.VISIBLE
                tv40.visibility = View.VISIBLE
                tv60.visibility = View.VISIBLE
                tv80.visibility = View.VISIBLE
                tv100.visibility = View.VISIBLE
            }
        }
    }

}
