package com.example.sinoyd.frameapplication.KotlinFrame.View

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.GridView

/**
 * 作者：
 * 创建时间： 2017/ /
 * 版权：
 * 描述： com.sinoyd.frame.views 不滑动GirdView视图类    禁止滑动
 */
class MyGridView : GridView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        return if (ev.action == MotionEvent.ACTION_MOVE) {

            true  //禁止GridView滑动

        } else super.dispatchTouchEvent(ev)

    }

    //不出现滚动条
    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}
