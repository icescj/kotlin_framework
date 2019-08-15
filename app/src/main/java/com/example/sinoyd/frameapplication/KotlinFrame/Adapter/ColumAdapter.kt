package com.example.sinoyd.frameapplication.KotlinFrame.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.BaseAdapter
import android.graphics.drawable.GradientDrawable
import android.os.Build
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.GridviewColumdata
import com.example.sinoyd.frameapplication.KotlinFrame.Uitl.dip2px
import com.example.sinoyd.frameapplication.R


/**
 * 作者： scj
 * 创建时间： 2018/1/8
 * 版权：
 * 描述： com.example.sinoyd.gridviewcolumnargraphapplication.Adatper
 *                柱状图  适配器
 */

class ColumAdapter(var context: Context, var list: List<GridviewColumdata>, var colors: IntArray, var roundRadius: Float) : BaseAdapter() {

    var max: Double = 0.0

    init {
        list
                .asSequence()
                .filter { it.value > max }
                .forEach { max = it.value }
    }

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        var view: View
        var holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.frame_item_colum_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        //当前数据
        var data = list[p0]
        holder.gv_column_name!!.text = data.name
        holder.gv_column_data!!.text = data.value.toString()
        //dp转px   params单位是px
        //其中 200 指的是 gridview控件在xml中设置的200dp
        holder.params!!.height = ((dip2px(context, (200 - 30 - 30).toFloat()) * data.value) / max).toInt()
        Log.i("scj", "value:${data.value}  heiht:${holder.params!!.height}")
        holder.gv_column_bg!!.layoutParams = holder.params


        //柱状图的背景颜色

        val myGrad = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
        myGrad.cornerRadius = roundRadius
        holder.gv_column_bg!!.setBackgroundDrawable(myGrad)

        return view
    }

    internal inner class ViewHolder(view: View) {
        var gv_column_data: TextView? = null
        var gv_column_bg: TextView? = null
        var gv_column_name: TextView? = null
        var params: RelativeLayout.LayoutParams? = null

        init {
            gv_column_data = view.findViewById(R.id.gv_column_data)
            gv_column_bg = view.findViewById(R.id.gv_column_bg)
            gv_column_name = view.findViewById(R.id.gv_column_name)
            params = gv_column_bg!!.layoutParams as RelativeLayout.LayoutParams?
        }
    }


    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(p0: Int): Long = 0L

    override fun getCount(): Int = list.size


}