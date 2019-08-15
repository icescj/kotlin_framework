package com.example.sinoyd.frameapplication.KotlinFrame.Mode

import android.content.Context
import android.graphics.Color
import android.widget.GridView
import com.example.sinoyd.frameapplication.KotlinFrame.Adapter.NewGridviewChooseAdapter
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.Keyvalue
import org.jetbrains.anko.onItemClick

/**
 * 作者： scj
 * 创建时间： 2018/3/23
 * 版权：  girdview实现的单选和多选的一个逻辑处理类
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.Mode
 */

class SingleMore constructor() {
    var listdata: ArrayList<Keyvalue> = ArrayList()
    var gridview: GridView? = null
    var con: Context? = null
    var adapter: NewGridviewChooseAdapter? = null
    var only: Boolean = false
    val TYPEID: String = "ID"
    val TYPENAME: String = "NAME"
    var selectedbgcolor: Int = Color.parseColor("#FF9224")
    var defaultbgcolor: Int = Color.parseColor("#33000000")

    constructor(context: Context, gv: GridView, list: ArrayList<Keyvalue>, only: Boolean) : this() {
        this.listdata = list
        this.gridview = gv
        this.con = context
        this.only = only
    }


    fun setcolor(defbgcolor: String, selbgcolor: String): SingleMore {
        this.defaultbgcolor = Color.parseColor(defbgcolor)
        this.selectedbgcolor = Color.parseColor(selbgcolor)
        return this
    }


    fun show() {
        listdata[0].isselected = true
        adapter = NewGridviewChooseAdapter(listdata, con!!, only, defaultbgcolor, selectedbgcolor)//true:单选   false:多选
        gridview!!.adapter = adapter
        gridview!!.onItemClick { adapterView, view, i, l ->
            listdata = changedata(i, listdata, adapter!!.only)
            adapter!!.notifyDataSetChanged()
        }
    }

    //单击进行判断进行数据整理
    fun changedata(i: Int, listkeyvaluename: ArrayList<Keyvalue>, only: Boolean): ArrayList<Keyvalue> {
        if (only) {
            for (item in listkeyvaluename) {
                item.isselected = false
            }
            listkeyvaluename[i].isselected = true
        } else {
            if (judge(i, listkeyvaluename)) {

            } else {
                listkeyvaluename[i].isselected = !(listkeyvaluename[i].isselected)
            }

        }
        return listkeyvaluename
    }

    //多选的时候，判断  当只有一个true的时候，点击的是不是当前true的那个
    fun judge(postion: Int, list: ArrayList<Keyvalue>): Boolean {
        var n = 0  //当前有几个true
        var j = 0  //当前是true的位置
        for ((x, item) in list.withIndex()) {
            if (item.isselected) {
                n++
                j = x
            }
        }

        if (n == 1) {
            if (postion == j) {
                return true
            }
        }

        return false

    }

    // 获取被选中的参数
    fun getparameter(type: String): String {
        var parameter = ""
        if (adapter!!.datas.isEmpty()) {
            parameter = ""
        }
        if (adapter!!.datas.size == 1) {
            parameter = if (type == TYPEID) {
                adapter!!.datas[0].keyid
            } else {
                adapter!!.datas[0].valuename
            }
        }
        if (adapter!!.datas.size > 1) {
            for (item in adapter!!.datas) {
                if (item.isselected) {
                    parameter = if (type == TYPEID) {
                        parameter + item.keyid + ","
                    } else {
                        parameter + item.valuename + ","
                    }

                }
            }
            parameter = parameter.substring(0, parameter.length - 1)
        }
        return parameter
    }

    //重置
    fun Reset() {
        adapter!!.setChoiceType(false)
    }
}