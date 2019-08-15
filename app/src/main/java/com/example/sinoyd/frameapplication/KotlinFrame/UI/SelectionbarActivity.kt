package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.test_activity_selectionbar.*
import org.jetbrains.anko.toast

/**
 * 选择栏   demo  使用方法
 */
class SelectionbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_selectionbar)

        testsb.setdefaulttext("Plan:A", "Plan:B")//设置默认的文字
                .setdefaultSelectBt(testsb.LEFTTRUE)//设置默认选中的raidobutton
                .settextcolor("#ffffff")//设置字体的颜色
                .setradiisleft(floatArrayOf(10f, 10f, 0f, 0f, 0f, 0f, 10f, 10f))//设置左边是个角的弧度
                .setradiisright(floatArrayOf(0f, 0f, 10f, 10f, 10f, 10f, 0f, 0f))//设置右边是个角的弧度
                .setradiobg("#550f82ff", "#0f82ff")//设置默认颜色，选中的颜色
                .setOnCheckedChangeListener { group, checkedId ->
                    when (checkedId) {
                        R.id.rb_bt1 -> {
                            toast("左边")
                        }
                        R.id.rb_bt2 -> {
                            toast("右边")
                        }
                    }
                }

        tvusage.text = """
//设置默认的文字
testsb.setdefaulttext("Plan:A", "Plan:B")
//设置默认选中的raidobutton
.setdefaultSelectBt(testsb.LEFTTRUE)
//设置字体的颜色
.settextcolor("#ffffff")
//设置左边是个角的弧度
.setradiisleft(floatArrayOf(10f, 10f, 0f, 0f, 0f, 0f, 10f, 10f))
//设置右边是个角的弧度
.setradiisright(floatArrayOf(0f, 0f, 10f, 10f, 10f, 10f, 0f, 0f))
//设置默认颜色，选中的颜色
.setradiobg("#550f82ff", "#0f82ff")
.setOnCheckedChangeListener { group, checkedId ->
when (checkedId) {
R.id.rb_bt1 -> {
toast("左边")
}
R.id.rb_bt2 -> {
toast("右边")
}
}
}
            """
    }
}
