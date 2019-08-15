package com.example.sinoyd.frameapplication.KotlinFrame.Dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.example.sinoyd.frameapplication.R
import com.sinoyd.environmentsz.Kotlin.isDateOneBigger
import org.jetbrains.anko.backgroundDrawable

import org.jetbrains.anko.toast

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * 起始结束日期选择弹出框 Copyright (c) 2015
 *
 * @类型名称：DateSelectDialog
 * @创建人：
 * @创建日期：2015-1-27
 * @维护人员：
 * @维护日期：
 * @功能摘要：
 */
class SingleDateSelectDialog : Dialog, View.OnClickListener {

    private var dateSelectListener: DateSelectListener? = null
    var startTimeStr: String? = null
    var endTimeStr: String? = null

    var startDatePicker: DatePicker? = null
    var endDatePicker: DatePicker? = null
    var format = "yyyy/MM/dd"
    var date: Date = Date()
    var themecolor: Int = Color.parseColor("#0f82ff")
    var startdatetext: TextView = TextView(context)
    var okbtn: Button = Button(context)
    var cancelbtn: Button = Button(context)

    interface DateSelectListener {
        fun selectDate(startDate: String)
    }

    fun setDateSelectListener(dateSelectListener: DateSelectListener): SingleDateSelectDialog {
        this.dateSelectListener = dateSelectListener
        return this
    }

    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener) {
        initView()
    }

    constructor(context: Context, theme: Int, format: String = "yyyy/MM/dd", themecolorstr: String = "#0f82ff") : super(context, R.style.Theme_Dialog_Transparent) {
        this.format = format
        this.themecolor = Color.parseColor(themecolorstr)
        initView()
    }

    constructor(context: Context) : super(context) {
        initView()
    }


    private fun initView() {
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.frame_single_date_selelct_layout)
        startdatetext = findViewById(R.id.start_date_text)
        okbtn = findViewById(R.id.ok_btn)
        cancelbtn = findViewById(R.id.cancel_btn)

        okbtn.setOnClickListener(this)
        cancelbtn.setOnClickListener(this)

        startdatetext.setTextColor(themecolor)

        val mygad = GradientDrawable()
        mygad.setStroke(0, themecolor)
        mygad.setColor(themecolor)
        mygad.cornerRadius = 5f

        okbtn.backgroundDrawable = mygad
        cancelbtn.backgroundDrawable = mygad

        startDatePicker = findViewById<View>(R.id.start_date_picker) as DatePicker
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.ok_btn -> {
                if (dateSelectListener != null) {
                    val dateFormat = SimpleDateFormat(format)
                    val calendar = Calendar.getInstance()
                    calendar.set(startDatePicker!!.year, startDatePicker!!.month, startDatePicker!!.dayOfMonth)
                    val startDate = dateFormat.format(calendar.time)
                    calendar.set(endDatePicker!!.year, endDatePicker!!.month, endDatePicker!!.dayOfMonth)
                    dateSelectListener!!.selectDate(startDate)
                }
                dismiss()
            }
            R.id.cancel_btn -> dismiss()
            else -> dismiss()
        }
    }

    /***
     * 设置日期
     *
     * @param startTimeStr
     * @param endTimeStr
     */
    fun setStartAndEndDate(startTimeStr: String?, endTimeStr: String?) {
        if (startTimeStr == null || endTimeStr == null)
            return
        this.startTimeStr = startTimeStr
        this.endTimeStr = endTimeStr
        val dateFormat = SimpleDateFormat(format)
        val calendar = Calendar.getInstance()
        val startDate: Date
        val endDate: Date
        try {
            if (android.os.Build.VERSION.SDK_INT >= 11) {
                startDate = dateFormat.parse(startTimeStr)
                calendar.time = startDate
                startDatePicker!!.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null)
                endDate = dateFormat.parse(endTimeStr)
                calendar.time = endDate
                endDatePicker!!.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

}
