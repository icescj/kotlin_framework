package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shenchuanjiang.kotlin1013test.CommonSelector
import com.example.sinoyd.frameapplication.KotlinFrame.Dataclass.getidnamedata
import com.example.sinoyd.frameapplication.R
import kotlinx.android.synthetic.main.activity_imitate_ios.*
import org.jetbrains.anko.act
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

class ImitateIOSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imitate_ios)
        bt.onClick {
            var comm = CommonSelector(act, getidnamedata(), object : CommonSelector.OnSelectClickListener {
                override fun onCommonItemSelect(postions: Int) {
                    toast(getidnamedata()[postions].name)
                }
            })
            comm.showPop()
        }
        tvusage.text = """
//新建对象，参数里面包含一个监听事件
var comm = CommonSelector(activity, getidnamedata(), object : CommonSelector.OnSelectClickListener {
override fun onCommonItemSelect(postions: Int) {
//TODO 我需要对我拿到到数据干嘛？
}
})
//控件的显示
comm.showPop()
            """
    }
}
