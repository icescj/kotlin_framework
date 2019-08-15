package com.example.sinoyd.frameapplication.KotlinFrame.Adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.Keyvalue
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.frame_item_gvchooselayout.view.*

/**
 * 作者： scj
 * 创建时间： 2018/1/11
 * 版权：
 * 描述： com.sinoyd.Code.Adapter   单选 多选功能的适配器
 *
 * @param only true:单选   false：多选
 * @param datas 数据源
 */


class NewGridviewChooseAdapter(var datas: List<Keyvalue>, var context: Context, var only: Boolean, var defaultbgcolor: Int, var selectedbgcolor: Int) : BaseAdapter() {


    //重置
    fun setChoiceType(type: Boolean) {
        for (item in datas) {
            item.isselected = type
        }
        datas[0].isselected = true
        notifyDataSetChanged()
    }


    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        var view: View
        var holder: ViewHolder?
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.frame_item_gvchooselayout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        //显示数据
        holder.cbtext!!.text = datas[p0].valuename
        holder.cbtext!!.tag = datas[p0].valuename

        if (only) {
            //单选状态
            holder.cbyes!!.visibility = GONE
            if (datas[p0].isselected) {
                holder.cbll!!.setBackgroundColor(selectedbgcolor)
            } else {
                holder.cbll!!.setBackgroundColor(defaultbgcolor)
            }
        } else {
            //多选状态
            if (datas[p0].isselected) {
                holder.cbyes!!.visibility = VISIBLE
                holder.cbyes!!.setColorFilter(selectedbgcolor)
                holder.cbll!!.setBackgroundResource(R.drawable.frame_shape_gvchoooseboder)
                var myGrad: GradientDrawable = holder.cbll!!.background as GradientDrawable
                myGrad.setStroke(2, selectedbgcolor)
            } else {
                holder.cbyes!!.visibility = GONE
                holder.cbll!!.setBackgroundColor(defaultbgcolor)
            }
        }
        return view
    }

    override fun getItem(p0: Int) = datas[p0]

    override fun getItemId(p0: Int) = 0L

    override fun getCount() = datas.size


    internal inner class ViewHolder(view: View) {
        var cbtext: TextView? = null
        var cbll: RelativeLayout? = null
        var cbyes: ImageView? = null

        init {
            cbtext = view.cb_text
            cbll = view.cb_ll
            cbyes = view.cb_text_yes
        }
    }
}



