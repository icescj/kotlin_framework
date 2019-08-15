package com.example.sinoyd.frameapplication.KotlinFrame.Adapter

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.Idname
import org.jetbrains.anko.*

/**
 * 作者： scj
 * 创建时间： 2018/3/21
 * 版权： 一个没有xml文件的非常简单的适配器，一个textview
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.Adapter  简单的一个适配器  一个Textview，居中显示数据
 */


class Todoadapter<T : Idname>(val list: ArrayList<T>) : BaseAdapter() {
    override fun getView(i: Int, v: View?, parent: ViewGroup?): View {
        return with(parent!!.context) {
            linearLayout {
                textView {
                    gravity = Gravity.CENTER
                    text = list[i].name
                    textSize = 16f
//                    textColor = context.resources.getColor(R.color.titlelbue)
                    textColor = Color.parseColor("#0f82ff")//字体显示的背景
                    padding = dip(10)
                }.lparams(matchParent, wrapContent)
            }

        }

    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0L
    }

    override fun getCount(): Int {
        return list.size
    }

}
