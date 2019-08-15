package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.getkeyvaluedata
import com.example.sinoyd.frameapplication.KotlinFrame.Mode.SingleMore
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.test_activity_single_more.*
import org.jetbrains.anko.onClick

/**
 * Girdview实现单选及多选  demo 使用方法
 */
class SingleMoreActivity : AppCompatActivity() {

    var singlemore: SingleMore = SingleMore()
    var moresingle: SingleMore = SingleMore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_single_more)
        /***************单选框***********************/
        //创建单选框对象  数据类型[Keyvalue]
        singlemore = SingleMore(this, gvsingle, getkeyvaluedata(), true)   //单选框
        //设置默认颜色，选中颜色，显示
        singlemore.setcolor("#550f82ff", "#0f82ff").show()

        tvsingle.text="""
【控件上需要设置的：
android:horizontalSpacing="5dp"
android:numColumns="2"】
//创建单选框对象  数据类型[Keyvalue]
singlemore = SingleMore(this, gvsingle, getkeyvaluedata(), true)   //单选框
//设置默认颜色，选中颜色，显示
singlemore.setcolor("#550f82ff", "#0f82ff").show()
            """

        bt1.onClick {
            //获取选中对象
            tv1.text = singlemore.getparameter(singlemore.TYPEID)
        }
        bt11.onClick {
            //重置
            singlemore.Reset()
            bt1.performClick()
        }
        /***************多选框***********************/
        //创建多选框对象
        moresingle = SingleMore(this, gvmore, getkeyvaluedata(), false)   //多选框
        //设置默认颜色，选中颜色，显示
        moresingle.setcolor("#550f82ff", "#0f82ff").show()

        tvmore.text="""
【控件上需要设置的：
android:horizontalSpacing="5dp"
android:numColumns="2"】
//创建多选框对象  数据类型[Keyvalue]
moresingle = SingleMore(this, gvmore, getkeyvaluedata(), false)   //多选框
//设置默认颜色，选中颜色，显示
moresingle.setcolor("#550f82ff", "#0f82ff").show()
            """
        bt2.onClick {
            //获取选中对象
            tv2.text = moresingle.getparameter(singlemore.TYPENAME)
        }
        bt22.onClick {
            //重置
            moresingle.Reset()
            bt2.performClick()
        }

    }
}
