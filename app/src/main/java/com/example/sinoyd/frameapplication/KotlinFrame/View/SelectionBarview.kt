package com.example.sinoyd.frameapplication.KotlinFrame.View

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.sinoyd.frameapplication.R
import org.jetbrains.anko.textColor


/**
 * 作者： scj
 * 创建时间： 2018/3/23
 * 版权：
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.View
 */

class SelectionBarview(context: Context, attr: AttributeSet) : RadioGroup(context, attr) {
    var rg: RadioGroup = RadioGroup(context)
    var rbleft: RadioButton = RadioButton(context)
    var rbright: RadioButton = RadioButton(context)
    val LEFTTRUE = "LEFTTRUE"
    val RIGHTTRUE = "RIGHTTRUE"
    var radiisleft = floatArrayOf(10f, 10f, 0f, 0f, 0f, 0f, 10f, 10f)
    var radiisright = floatArrayOf(0f, 0f, 10f, 10f, 10f, 10f, 0f, 0f)
    var mOnCheckedChangeListener: RadioGroup.OnCheckedChangeListener? = null

    //初始化界面信息
    init {
        LayoutInflater.from(context).inflate(R.layout.frame_ui_selectionbar_layout, this)
        rg = findViewById(R.id.rg_pointchoose)
        rbleft = findViewById<RadioButton>(R.id.rb_bt1)
        rbright = findViewById(R.id.rb_bt2)
    }


    //设置默认的选中状态的RadioButton
    fun setdefaultSelectBt(type: String): SelectionBarview {
        when (type) {
            "LEFTTRUE" -> {
                rbleft.isChecked = true
            }
            "RIGHTTRUE" -> {
                rbright.isChecked = true
            }
        }

        return this
    }

    //设置四个角弧度  [左边]
    fun setradiisleft(value: FloatArray): SelectionBarview {
        this.radiisleft = value
        return this
    }

    //设置四个角弧度  [左边]
    fun setradiisright(value: FloatArray): SelectionBarview {
        this.radiisright = value
        return this
    }


    //设置radiobutton上的字体
    fun setdefaulttext(lefttext: String, righttext: String): SelectionBarview {
        rbleft.text = lefttext
        rbright.text = righttext
        return this
    }

    //设置radiobutton字体的颜色
    fun settextcolor(color: String = "#000"): SelectionBarview {
        rbleft.textColor = Color.parseColor(color)
        rbright.textColor = Color.parseColor(color)
        return this
    }

    @SuppressLint("ResourceType")
    fun setradiobg(defaultcolor: String, selectcolor: String): SelectionBarview {
        var defcolor: Int = Color.parseColor(defaultcolor)
        var selcolor: Int = Color.parseColor(selectcolor)
        /**左边的一个radiobutton**/
        //创建一个默认的背景
        val defaultgradientDrawable_left = GradientDrawable()
        defaultgradientDrawable_left.setColor(defcolor)//设置颜色
        defaultgradientDrawable_left.setStroke(2, selcolor)//设置边框
        defaultgradientDrawable_left.cornerRadii = radiisleft//设置是个角度

        //创建一个被选中的背景
        val selectgradientDrawable_left = GradientDrawable()
        selectgradientDrawable_left.setColor(selcolor)
        selectgradientDrawable_left.setStroke(2, selcolor)
        selectgradientDrawable_left.cornerRadii = radiisleft
        /**右边的一个radiobutton**/
        //创建一个默认的背景
        val defaultgradientDrawable_right = GradientDrawable()
        defaultgradientDrawable_right.setColor(defcolor)
        defaultgradientDrawable_right.setStroke(2, selcolor)
        defaultgradientDrawable_right.cornerRadii = radiisright
        //创建一个被选中的背景
        val selectgradientDrawable_right = GradientDrawable()
        selectgradientDrawable_right.setColor(selcolor)
        selectgradientDrawable_right.setStroke(2, selcolor)
        selectgradientDrawable_right.cornerRadii = radiisright

        //增加一个状态drawable作为左边radiobutton的样式
        val stateListDrawable_left = StateListDrawable()
        stateListDrawable_left.addState(intArrayOf(-android.R.attr.state_checked), defaultgradientDrawable_left)
        stateListDrawable_left.addState(intArrayOf(android.R.attr.state_checked), selectgradientDrawable_left)
        rbleft.setBackgroundDrawable(stateListDrawable_left)

        //增加一个状态drawable作为右边radiobutton的样式
        val stateListDrawable_right = StateListDrawable()
        stateListDrawable_right.addState(intArrayOf(-android.R.attr.state_checked), defaultgradientDrawable_right)
        stateListDrawable_right.addState(intArrayOf(android.R.attr.state_checked), selectgradientDrawable_right)
        rbright.setBackgroundDrawable(stateListDrawable_right)


        rg.setOnCheckedChangeListener { group, checkedId ->
            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener!!.onCheckedChanged(group, checkedId)
            }
        }

        return this
    }

    override fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        mOnCheckedChangeListener = listener
    }


}