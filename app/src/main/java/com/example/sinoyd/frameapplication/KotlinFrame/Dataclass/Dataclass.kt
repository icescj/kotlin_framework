package com.example.sinoyd.frameapplication.KotlinFrame.Dataclass

import android.app.Fragment

/**
 * 作者： scj
 * 创建时间： 2018/3/21
 * 版权：
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.Dataclass
 */

// 首页底部数据的数据类
data class Tabframe(var imageid: Int, var titlename: String, var frame: Fragment)

// 二维列表中的数据类
data class GVkeyvalue(var keytime: GVkeytime, var valueunit: GVkeytime, var kv: String)

// 二维列表中的数据类  子数据类
data class GVkeytime(var keyx: String, var timey: String)

//伪造一些数据  [二维数组中需要使用]
fun getTwodimensionallistdata(): ArrayList<GVkeyvalue> {
    var list: ArrayList<GVkeyvalue> = ArrayList()
    for (item in 0..10) {
        list.add(GVkeyvalue(GVkeytime(item.toString() + "沈", item.toString()), GVkeytime(item.toString() + "川", item.toString()), item.toString() + "江"))
    }
    return list
}

//柱状图中的数据类
data class GridviewColumdata(var name: String, var value: Double)

//伪造一些数据  [柱状图使用]
fun getGridviewColumdata(): ArrayList<GridviewColumdata> {
    var list: ArrayList<GridviewColumdata> = ArrayList()
    list.add(GridviewColumdata("数据1", 1.0))
    list.add(GridviewColumdata("数据2", 2.0))
    list.add(GridviewColumdata("数据3", 3.0))
    list.add(GridviewColumdata("数据4", 4.0))
    list.add(GridviewColumdata("数据5", 5.0))
    list.add(GridviewColumdata("数据6", 6.0))
    list.add(GridviewColumdata("数据7", 7.0))
    return list
}

//单选多选列表中所需要的数据类
data class Keyvalue(var keyid: String, var valuename: String, var isselected: Boolean = false)

//伪造一些数据  单选多选功能用
fun getkeyvaluedata(): ArrayList<Keyvalue> {
    var list: ArrayList<Keyvalue> = ArrayList()
    list.add(Keyvalue("1", "宋江"))
    list.add(Keyvalue("2", "晁盖"))
    list.add(Keyvalue("3", "卢俊义"))
    list.add(Keyvalue("4", "林冲"))
    list.add(Keyvalue("5", "李逵"))
    list.add(Keyvalue("6", "鲁智深"))
    list.add(Keyvalue("7", "吴用"))
    list.add(Keyvalue("8", "武松"))
    list.add(Keyvalue("9", "杨志"))
    list.add(Keyvalue("10", "燕青"))
    list.add(Keyvalue("11", "孙二娘 "))
    list.add(Keyvalue("12", "花荣"))
    list.add(Keyvalue("13", "柴进"))
    list.add(Keyvalue("14", "李应"))
    list.add(Keyvalue("15", "朱仝"))
    list.add(Keyvalue("16", "倚天剑"))
    return list
}


//仿ios弹出框 中间的数据泛型接口
interface Idname {
    var id: String
    var name: String
}

//实现Idname接口的数据类
data class Idnamevalue(override var id: String, override var name: String) : Idname

//伪造一些数据  仿ios弹出框功能用
fun getidnamedata(): ArrayList<Idnamevalue> {
    var list: ArrayList<Idnamevalue> = ArrayList()
    list.add(Idnamevalue("1", "宋江"))
    list.add(Idnamevalue("2", "晁盖"))
    list.add(Idnamevalue("3", "卢俊义"))
    list.add(Idnamevalue("4", "林冲"))
    list.add(Idnamevalue("5", "李逵"))
    list.add(Idnamevalue("6", "鲁智深"))
    list.add(Idnamevalue("7", "吴用"))
    list.add(Idnamevalue("8", "武松"))
    list.add(Idnamevalue("9", "杨志"))
    list.add(Idnamevalue("10", "燕青"))
    list.add(Idnamevalue("11", "孙二娘 "))
    list.add(Idnamevalue("12", "花荣"))
    list.add(Idnamevalue("13", "柴进"))
    list.add(Idnamevalue("14", "李应"))
    list.add(Idnamevalue("15", "朱仝"))
    list.add(Idnamevalue("16", "倚天剑"))
    return list
}
