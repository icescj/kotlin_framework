package com.example.sinoyd.frameapplication.KotlinFrame.View

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView

/**
 * @Description:联动的滑动scrollview
 */
class SyncScrollView : ScrollView {

    private var mView: View? = null

    constructor(context: Context) : super(context) {
        // TODO Auto-generated constructor stub
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        // TODO Auto-generated constructor stub
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (mView != null) {
            mView!!.scrollTo(l, t)
        }
    }

    /**
     * 设置联动的view
     *
     * @param view
     */
    fun setScrollView(view: View) {
        mView = view
    }
}