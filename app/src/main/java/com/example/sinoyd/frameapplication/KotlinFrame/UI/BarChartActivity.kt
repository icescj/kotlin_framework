package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.getGridviewColumdata
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.test_activity_bar_chart.*


/**
 * Girdview实现树状图   demo  使用方法 ？？
 */

class BarChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_bar_chart)
        //设置显示颜色，渐变3色  直接显示view
        barchart.setcolors("#990f82ff", "#990f82ff", "#0f82ff").setroundRadius(100f).show(getGridviewColumdata())

        tvusage.text = """
                 //设置颜色，渐变三色
                barchart.setcolors("#990f82ff", "#990f82ff", "#0f82ff")
                 //设置圆弧度
                .setroundRadius(100f)
                 //设置数据并显示  数据类型【GridviewColumdata】
                .show(getGridviewColumdata())
            """
    }
}
