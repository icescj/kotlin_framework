package com.example.sinoyd.frameapplication.KotlinFrame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.brightfuture.basemodule.Utils.Getpermission
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.Tabframe
import com.example.sinoyd.frameapplication.KotlinFrame.Fragment.OneFragment
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.frame_activity_main.*

class MainActivity : AppCompatActivity() {

    var tabfragments: ArrayList<Tabframe> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_activity_main)
        //获取权限
        Getpermission.requestPermission(this)
        setbottomtab()
        setbottomtitlestyle()
        setDefaultFragment()
    }

    private fun setbottomtab() {
        tabfragments = ArrayList()
        tabfragments.add(Tabframe(R.mipmap.icon_one, "土", OneFragment("土")))
        tabfragments.add(Tabframe(R.mipmap.icon_hot, "火", OneFragment("火")))
        tabfragments.add(Tabframe(R.mipmap.icon_water, "水", OneFragment("水")))
        tabfragments.add(Tabframe(R.mipmap.icon_electric, "木", OneFragment("木")))
        tabfragments.add(Tabframe(R.mipmap.icon_gold, "金", OneFragment("金")))
    }

    //设置默认的Fragment   默认第一个界面"0"
    private fun setDefaultFragment() {
        val fm = fragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.frgContent, tabfragments[0].frame)
        transaction.commit()
    }


    //设置底部导航栏风格及参数
    private fun setbottomtitlestyle() {
        for (item in tabfragments) {
            bottom_navigation_bar.addItem(BottomNavigationItem(item.imageid, item.titlename))
        }
        bottom_navigation_bar
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                    override fun onTabReselected(position: Int) {
                        //重新被选中
                    }

                    override fun onTabUnselected(position: Int) {
                        //未被选中
                    }

                    override fun onTabSelected(position: Int) {
                        //选中
                        val fm = fragmentManager
                        val transaction = fm.beginTransaction()
                        transaction.replace(R.id.frgContent, tabfragments[position].frame)
                        transaction.commit()
                    }
                })
                .setActiveColor("#FF107FFD") //选中颜色
                .setInActiveColor("#e9e6e6") //未选中颜色
                .setBarBackgroundColor("#FFFFFF")//导航栏背景色
                .setFirstSelectedPosition(0)//默认的位置
//                .setScrollable(true)//滑动设置，暂时版本还未开放
                .initialise()
    }
}
