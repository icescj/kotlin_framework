package com.example.sinoyd.frameapplication.KotlinFrame.View

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.sinoyd.frameapplication.KotlinFrame.Adapter.LiftAdapter
import com.example.sinoyd.frameapplication.KotlinFrame.Adapter.MiddleAdapter
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.GVkeytime
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.GVkeyvalue
import com.example.sinoyd.frameapplication.KotlinFrame.Uitl.dip2px
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.frame_ui_twodimensionallist.view.*

/**
 * 作者： scj
 * 创建时间： 2018/3/21
 * 版权：二维列表视图
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.View
 */

class Twodimensionallistview(context: Context, attr: AttributeSet) : RelativeLayout(context, attr) {

    //整个数据
    var list: List<GVkeyvalue> = ArrayList()
    //行数据
    var linevalue = java.util.ArrayList<GVkeytime>()
    //列的数据
    var columnkey = java.util.ArrayList<GVkeytime>()
    //计算 中间的值
    var listkv = java.util.ArrayList<GVkeyvalue>()

    var leftgridview: GridView = GridView(context)
    var rightgridview: GridView = GridView(context)
    var middlegridview: GridView = GridView(context)
    var leftSyncScrollView: SyncScrollView = SyncScrollView(context)
    var rightSyncScrollView: SyncScrollView = SyncScrollView(context)

    //初始化界面信息
    init {
        LayoutInflater.from(context).inflate(R.layout.frame_ui_twodimensionallist, this)
        leftSyncScrollView = findViewById(R.id.SyncScrollViewlift)
        leftgridview = findViewById(R.id.gvkeyvalue_left)
        rightgridview = findViewById(R.id.gvkeyvalue_right)
        middlegridview = findViewById(R.id.gvkeyvalue_middle)
        rightSyncScrollView = findViewById(R.id.SyncScrollViewright)
    }

    //初始化界面的一些设置
    fun initview(): Twodimensionallistview {
        //lift 与 middle gridview 上下滑动监听绑定
        SyncScrollViewlift.setScrollView(SyncScrollViewright)
        SyncScrollViewright.setScrollView(SyncScrollViewlift)
        return this
    }

    //设置数据
    fun setdata(list: List<GVkeyvalue>): Twodimensionallistview {
        this.list = list
        shuntdata(list)
        return this
    }

    //分流数据  【拆成行数据，列数据】
    private fun shuntdata(list: List<GVkeyvalue>): Twodimensionallistview {
        linevalue = getlinevalue(list)
        columnkey = getcolumnvalue(list)
        listkv = getkv(list, linevalue, columnkey)
        return this
    }

    //计算 行的数据
    fun getlinevalue(list: List<GVkeyvalue>): java.util.ArrayList<GVkeytime> {
        var result = java.util.ArrayList<GVkeytime>()
        list.mapTo(result) { GVkeytime(it.valueunit.keyx, it.valueunit.timey) }
        var restltto = java.util.ArrayList<GVkeytime>()
        for (item in result) {
            if (!restltto.contains(item)) {
                restltto.add(item)
            }
        }
        return restltto
    }

    //计算 列的数据
    fun getcolumnvalue(list: List<GVkeyvalue>): java.util.ArrayList<GVkeytime> {
        var result = java.util.ArrayList<GVkeytime>()
        list.mapTo(result) { GVkeytime(it.keytime.keyx, it.keytime.timey) }
        var restltto = java.util.ArrayList<GVkeytime>()
        for (item in result) {
            if (!restltto.contains(item)) {
                restltto.add(item)
            }
        }
        return restltto
    }

    //计算 中间的值
    fun getkv(list: List<GVkeyvalue>, linevalue: java.util.ArrayList<GVkeytime>, columnkey: java.util.ArrayList<GVkeytime>): java.util.ArrayList<GVkeyvalue> {
        var result = java.util.ArrayList<GVkeyvalue>()
        for (itemx in columnkey) {
            for (itemy in linevalue) {
                var gVkeyvalue: GVkeyvalue = GVkeyvalue(GVkeytime("", ""), GVkeytime("", ""), "")
                gVkeyvalue.keytime.keyx = itemx.keyx
                gVkeyvalue.keytime.timey = itemx.timey
                gVkeyvalue.valueunit.keyx = itemy.keyx
                gVkeyvalue.valueunit.timey = itemy.timey
                gVkeyvalue.kv = "--"
                for (itemz in list) {
                    if (itemx == itemz.keytime && itemy == itemz.valueunit) {
                        if (itemz.kv != "") {
                            gVkeyvalue.kv = itemz.kv
                        } else {
                            gVkeyvalue.kv = "--"
                        }
                    }
                }
                result.add(gVkeyvalue)
            }
        }
        return result
    }


    //显示数据
    fun show(): Twodimensionallistview {
        //设置right gridview的布局大小 并显示数据
        setrightgridview(context, linevalue, gvkeyvalue_right)

        //设置lift gridview的布局大小 并显示数据
        setliftgridview(context, columnkey, gvkeyvalue_left)

        //设置middle gridview的布局大小  并显示数据
        setmiddlegridview(context, listkv, gvkeyvalue_middle, linevalue)
        return this
    }

    /***
     * gridview 行数据显示 right
     **/
    fun setrightgridview(context: Context, list: java.util.ArrayList<GVkeytime>, right: GridView) {

        var size = list.size
//      val dm = DisplayMetrics()
//      context.windowManager.defaultDisplay.getMetrics(dm)
//      val density = dm.density
//      val allWidth = (80 * size.toFloat() * density).toInt()   //80 是每一个item的宽度
//      val itemWidth = (80 * density).toInt()
        val params = LinearLayout.LayoutParams(dip2px(context, 80f * size), LinearLayout.LayoutParams.WRAP_CONTENT)
        right.layoutParams = params// 设置GirdView布局参数
        right.columnWidth = dip2px(context, 80f)// 列表项宽
//      right.horizontalSpacing = 10// 列表项水平间距
        right.stretchMode = GridView.NO_STRETCH
        right.numColumns = size
        var ladapter = LiftAdapter(list, context)
        right.adapter = ladapter

    }


    /***
     * gridview 列数据显示 lift
     **/
    fun setliftgridview(context: Context, list: java.util.ArrayList<GVkeytime>, lift: GridView) {
        var ladapter = LiftAdapter(list, context)
        lift.adapter = ladapter
    }

    /***
     * gridview 行数据显示 middle
     **/
    fun setmiddlegridview(context: Context, listkv: java.util.ArrayList<GVkeyvalue>, middel: GridView, linevalue: java.util.ArrayList<GVkeytime>) {

        var size = linevalue.size
//      val dm = DisplayMetrics()
//      context.windowManager.defaultDisplay.getMetrics(dm)
//      val density = dm.density
//      val allWidth = (80 * size.toFloat() * density).toInt()   //80 是每一个item的宽度  转换成px
//      val itemWidth = (80 * density).toInt()
        val params = FrameLayout.LayoutParams(dip2px(context, 80f * size), FrameLayout.LayoutParams.WRAP_CONTENT)
        middel.layoutParams = params// 设置GirdView布局参数
        middel.columnWidth = dip2px(context, 80f)// 列表项宽
//      middel.horizontalSpacing = 10// 列表项水平间距
        middel.stretchMode = GridView.NO_STRETCH
        middel.numColumns = size

        var madapter = MiddleAdapter(listkv, context)
        middel.adapter = madapter


    }
}