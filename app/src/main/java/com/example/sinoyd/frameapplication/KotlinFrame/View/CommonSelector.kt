package com.example.shenchuanjiang.kotlin1013test

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface.DEFAULT_BOLD
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.TextView
import com.example.sinoyd.frameapplication.KotlinFrame.Adapter.Todoadapter
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.Idname
import com.example.sinoyd.frameapplication.R
import org.jetbrains.anko.*

/**
 * Created by scj on 2017/10/14.
 */


//选择器
/**
 * CommonSelector简单的选择器  【单选】
 * @param activity 当前界面activity
 * @param v popwindow显示位置的参照物
 * @param list popwindow上显示的数据
 * @param callback 回调接听，将选择的数据穿出进行相应操作
 */
class CommonSelector<T : Idname>(val activity: Activity, val list: ArrayList<T>, val callback: OnSelectClickListener) {

    var popupWindow: PopupWindow? = null
    var popupWindowView: View? = null
    var listview: ListView? = null
    var linearlayout: LinearLayout? = null
    var mianlinearlayout: LinearLayout? = null
    var params: LinearLayout.LayoutParams? = null
    var lvparams: LinearLayout.LayoutParams? = null
    var mainparams: LinearLayout.LayoutParams? = null
    var paramstv: LinearLayout.LayoutParams? = null
    var textv: TextView? = null

    init {
        /**显示的布局**/
        //设置父布局
        mianlinearlayout = LinearLayout(activity)
        mianlinearlayout!!.padding = 30
//        mianlinearlayout!!.orientation = LinearLayout.VERTICAL
        mainparams = LinearLayout.LayoutParams(matchParent, matchParent)
        mainparams!!.gravity = Gravity.BOTTOM
        mianlinearlayout!!.layoutParams = mainparams
//        mianlinearlayout!!.backgroundColor = activity.resources.getColor(R.color.gray_al)
        mianlinearlayout!!.backgroundColor = Color.parseColor("#55666666")//整体的背景

        //设置LinearLayout布局
        linearlayout = LinearLayout(activity)
        params = LinearLayout.LayoutParams(matchParent, wrapContent)
        params!!.gravity = Gravity.BOTTOM


        linearlayout!!.layoutParams = params
        linearlayout!!.orientation = LinearLayout.VERTICAL
//       var draw: Drawable = ColorDrawable()
//       draw.alpha = 0
//       linearlayout!!.backgroundDrawable = draw
//       linearlayout!!.padding = 30
//       linearlayout!!.background.alpha = 0
//       linearlayout!!.setDrawingCacheBackgroundColor(Color.argb(0, 255, 255, 255))
//       linearlayout!!.backgroundColor = Color.argb(100, 0, 255, 0)
        //设置listview布局

        lvparams = if (list.size > 6) {
            LinearLayout.LayoutParams(matchParent, 400)
        } else {
            LinearLayout.LayoutParams(matchParent, wrapContent)
        }

        listview = ListView(activity)
        listview!!.layoutParams = lvparams
//        listview!!.setBackgroundColor(Color.RED)
        listview!!.backgroundDrawable = activity.resources.getDrawable(R.drawable.frame_shape_boderwhite)
        //设置listview的divider的颜色及宽度
        listview!!.divider = ColorDrawable(Color.GRAY)
        listview!!.dividerHeight = 1
        //将listview添加入Linearlayout
        linearlayout!!.addView(listview)
        //设置取消按钮
        paramstv = LinearLayout.LayoutParams(matchParent, 80)
        paramstv!!.topMargin = 30
        textv = TextView(activity)
        textv!!.text = "取消"
        textv!!.gravity = Gravity.CENTER
        textv!!.layoutParams = paramstv
        textv!!.textSize = 18f
//        textv!!.textColor = activity.resources.getColor(R.color.titlelbue)
        textv!!.textColor = Color.parseColor("#0f82ff")//取消字样的颜色
        textv!!.typeface = DEFAULT_BOLD
//        textv!!.setBackgroundColor(Color.RED)
        textv!!.backgroundDrawable = activity.resources.getDrawable(R.drawable.frame_shape_boderwhite)
        //将取消按钮添加入Linearlayout
        linearlayout!!.addView(textv)


        //将布局加入父布局
        mianlinearlayout!!.addView(linearlayout)
        initPop()
    }

    private fun initPop() {
        popupWindowView = (mianlinearlayout as View?)!!
        val dw: ColorDrawable = ColorDrawable(Color.GRAY)
        dw.alpha = 1
        popupWindow = PopupWindow(popupWindowView, matchParent, matchParent, true)
        popupWindow!!.setBackgroundDrawable(dw)
        //点击popipview之外监听事件
        //popupWindow!!.setOnDismissListener { }
        initEvent()
    }


    private fun initEvent() {
        var adapterr = Todoadapter(list)
        listview!!.adapter = adapterr
        listview!!.onItemClick { adapterView, view, i, l ->
            popupWindow!!.dismiss()
            callback.onCommonItemSelect(i)
        }
        //取消按钮监听事件
        textv!!.onClick { popupWindow!!.dismiss() }
        mianlinearlayout!!.onClick { popupWindow!!.dismiss() }
    }

    fun showPop(): Unit {
        if (popupWindow!!.isShowing) {
            return
        }
        popupWindow!!.showAtLocation(View(activity), Gravity.BOTTOM, 0, 0)

    }

    interface OnSelectClickListener {
        fun onCommonItemSelect(postions: Int): Unit
    }

}


///******
// * 构造适配器TodoAdapter
// * 作者：scj
// */
//class TodoAdapter(val list: ArrayList<String>) : BaseAdapter() {
//    override fun getView(i: Int, v: View?, parent: ViewGroup?): View {
//        return with(parent!!.context) {
//            var taskNum: Int = i + 1
//            //Layout for a list view item
//            linearLayout {
//                textView {
//                    gravity = Gravity.CENTER
//                    text = list[i]
//                    textSize = 16f
////                    textColor = context.resources.getColor(R.color.titlelbue)
//                    textColor = Color.parseColor("#0f82ff")//字体显示的背景
//                    padding = dip(5)
//                }.lparams(matchParent, wrapContent)
//            }
//
//        }
//
//    }
//
//    override fun getItem(position: Int): Any {
//        return list[position]
//    }
//
//    override fun getItemId(p0: Int): Long {
//        return 0L
//    }
//
//    override fun getCount(): Int {
//        return list.size
//    }
//
//}





