package com.example.sinoyd.frameapplication.KotlinFrame.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.GVkeytime
import com.example.sinoyd.frameapplication.R

/**
 * 作者： scj
 * 创建时间： 2018/1/15
 * 版权： 二维列表中，左边(右边)的girdview适配器
 * 描述： com.sinoyd.Code.Adapter
 */

class LiftAdapter(var list: ArrayList<GVkeytime>, var context: Context) : BaseAdapter() {
    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        var view: View
        var holder: ViewHolder?
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.frame_item_lift_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }


        holder.name!!.text = list[p0].keyx

        if (list[p0].timey.contains(":") || list[p0].timey.contains("/")) {
            holder.name2!!.text = list[p0].timey.substring(0, list[p0].timey.length - 3)
        } else {
            holder.name2!!.text = list[p0].timey
        }


        return view

    }

    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(p0: Int): Long = 0L

    override fun getCount(): Int = list.size


    internal inner class ViewHolder(view: View) {
        var name: TextView? = null
        var name2: TextView? = null

        init {
            name = view.findViewById(R.id.liftname)
            name2 = view.findViewById(R.id.liftname2)
        }
    }
}