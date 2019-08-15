package com.example.sinoyd.frameapplication.KotlinFrame.Fragment

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sinoyd.frameapplication.KotlinFrame.Adapter.Todoadapter
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.Idnamevalue
import com.example.sinoyd.frameapplication.KotlinFrame.UI.*
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.test_fragment_one.view.*
import org.jetbrains.anko.onItemClick
import org.jetbrains.anko.startActivity
import java.util.*

@SuppressLint("ValidFragment")
class OneFragment(var name: String = "首页") : Fragment() {
    var list: ArrayList<Idnamevalue> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val conview = inflater.inflate(R.layout.test_fragment_one, null)
        setview(conview)
        setlisterens(conview)
        return conview
    }

    //控件的获取
    private fun setview(conview: View) {
        list = ArrayList()
        list.add(Idnamevalue("0", "Gridview_实现柱状图"))
        list.add(Idnamevalue("1", "Gridview_实现单选or多选"))
        list.add(Idnamevalue("2", "二维列表"))
        list.add(Idnamevalue("3", "选择栏"))
        list.add(Idnamevalue("4", "仿ios弹出提示框"))
        list.add(Idnamevalue("5", "时间区域选择框"))
        list.add(Idnamevalue("6", "时间选择栏"))
        list.add(Idnamevalue("7", "单个时间选择,[datetime,date,time]"))
        list.add(Idnamevalue("8", "网络请求 Retrofit+RxJava＋OkHttp"))
        list.add(Idnamevalue("9", "硬件的一些信息参数"))
        list.add(Idnamevalue("10", "相机，人脸识别"))
        list.add(Idnamevalue("11", "调节系统音量"))
        list.add(Idnamevalue("12", "设置闹铃"))
        list.add(Idnamevalue("13", "看门狗watchdog"))
        list.add(Idnamevalue("14", "生成二维码"))
        list.add(Idnamevalue("15", "MINA心跳"))
        var todoadapter = Todoadapter(list)
        conview.testlistview.adapter = todoadapter
    }

    //控件的监听事件
    private fun setlisterens(conview: View) {
        conview.testlistview!!.onItemClick { _, _, i, _ ->
            when (i) {
                0 -> {
                    //柱状图
                    activity.startActivity<BarChartActivity>()
                }
                1 -> {
                    //单选or多选
                    activity.startActivity<SingleMoreActivity>()
                }
                2 -> {
                    //二维列表
                    activity.startActivity<TwodimensionallistActivity>()
                }
                3 -> {
                    //选择栏
                    activity.startActivity<SelectionbarActivity>()
                }
                4 -> {
                    //仿ios弹出提示框
                    activity.startActivity<ImitateIOSActivity>()
                }
                5 -> {
                    //时间区域选择框
                    activity.startActivity<TimeselectiondialogActivity>()
                }
                6 -> {
                    //时间选择栏
                    activity.startActivity<TimeSelectionBarActivity>()
                }
                7 -> {
                    //自定义格式选择框
                    activity.startActivity<TimeDialogdemoActivity>()
                }
                8 -> {
                    //网络
                    activity.startActivity<NetworkActivity>()
                }
                9 -> {
                    //当前电量
                    activity.startActivity<InfoActivity>()
                }
                10 -> {
                    //相机
                    activity.startActivity<PhotoActivity>()
                }
                11 -> {
                    //音量
                    activity.startActivity<TouchActivity>()
                }
                12 -> {
                    //闹铃
                    activity.startActivity<AlarmActivity>()
                }
                13 -> {
                    //喂狗
                    activity.startActivity<WatchDogActivity>()
                }
                14 -> {
                    //二维码
                    activity.startActivity<GenerateErActivity>()
                }
                15 -> {
                    //wifi
                    activity.startActivity<Mina2Activity>()
                }

            }

        }

    }


}