package com.example.sinoyd.frameapplication.KotlinFrame.View

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.RelativeLayout
import com.example.sinoyd.frameapplication.KotlinFrame.Adapter.ColumAdapter
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.GridviewColumdata
import com.example.sinoyd.frameapplication.R
import org.jetbrains.anko.windowManager

/**
 * 作者： scj
 * 创建时间： 2018/3/22
 * 版权：girdview实现的柱状图
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.View
 */


class Barchartview(context: Context, attr: AttributeSet) : RelativeLayout(context, attr) {

    var gv: GridView = GridView(context)

    var colors: IntArray = intArrayOf(Color.parseColor("#1f92ff"), Color.parseColor("#1f92ff"), Color.parseColor("#49c8ff"))

    var roundRadius: Float = 200f

    //初始化界面信息
    init {
        LayoutInflater.from(context).inflate(R.layout.frame_ui_barchart, this)
        gv = findViewById(R.id.gv_column)
    }

    fun setcolors(startcolor: String, midcolor: String, endcolor: String): Barchartview {
        colors[0] = Color.parseColor(startcolor)
        colors[1] = Color.parseColor(midcolor)
        colors[2] = Color.parseColor(endcolor)
        return this
    }

    fun setroundRadius(roundRadius: Float): Barchartview {
        this.roundRadius = roundRadius
        return this
    }


    fun show(list: List<GridviewColumdata>) {
        var size = list.size
        val dm = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(dm)
        val density = dm.density
        val allWidth = (70f * size.toFloat() * density).toInt()
        val itemWidth = (60 * density).toInt()
        val params = RelativeLayout.LayoutParams(allWidth, RelativeLayout.LayoutParams.MATCH_PARENT)

        gv.layoutParams = params// 设置GirdView布局参数
        gv.columnWidth = itemWidth// 列表项宽
        gv.horizontalSpacing = 10// 列表项水平间距
        gv.stretchMode = GridView.NO_STRETCH
        gv.numColumns = size//总长度

        //定义适配器
        var columAdapter = ColumAdapter(context, list, colors,roundRadius)

        gv.adapter = columAdapter

    }


}