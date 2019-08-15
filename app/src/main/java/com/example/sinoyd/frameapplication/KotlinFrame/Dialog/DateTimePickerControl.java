package com.example.sinoyd.frameapplication.KotlinFrame.Dialog;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;


import com.example.sinoyd.frameapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTimePickerControl extends AlertDialog implements View.OnClickListener {

    DatePicker dater;// 日期选择
    TimePicker timer;// 时间选择
    Button btn_Confirm;// 确定
    Button btn_Cancel;// 取消
    Context context;
    String flag = "datetime";
    private DateSelectListener m_DateSelectListener;
    private int bgcolor;
    GradientDrawable mygad = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.frame_dialog_datetimepicker);
        setCanceledOnTouchOutside(true);
        dater = (DatePicker) findViewById(R.id.dater);
        dater.setCalendarViewShown(false);
        timer = (TimePicker) findViewById(R.id.timer);
        timer.setIs24HourView(true);
        if (flag.equals("datetime")) {
            dater.setVisibility(View.VISIBLE);
            timer.setVisibility(View.VISIBLE);
            resizePikcer(dater);
            resizePikcer(timer);
        } else if (flag.equals("date")) {
            dater.setVisibility(View.VISIBLE);
            timer.setVisibility(View.GONE);
        } else {
            dater.setVisibility(View.GONE);
            timer.setVisibility(View.VISIBLE);
        }
        btn_Confirm = (Button) findViewById(R.id.btn_Confirm);
        btn_Cancel = (Button) findViewById(R.id.btn_Cancel);
        btn_Cancel.setOnClickListener(this);
        btn_Cancel.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
//                      btn_Cancel.setTextColor(bgcolor);
//                      btn_Cancel.setBackgroundColor(Color.WHITE);
                        mygad.setColor(Color.WHITE);
                        mygad.setCornerRadius(5f);
                        btn_Cancel.setBackgroundDrawable(mygad);
                        break;
                    case MotionEvent.ACTION_DOWN:
//                      btn_Cancel.setTextColor(Color.WHITE);
//                      btn_Cancel.setBackgroundColor(bgcolor);
                        mygad.setColor(bgcolor);
                        mygad.setCornerRadius(5f);
                        btn_Cancel.setBackgroundDrawable(mygad);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        btn_Confirm.setOnClickListener(this);
        btn_Confirm.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mygad.setColor(Color.WHITE);
                        mygad.setCornerRadius(5f);
                        btn_Confirm.setBackgroundDrawable(mygad);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        mygad.setColor(bgcolor);
                        mygad.setCornerRadius(5f);
                        btn_Confirm.setBackgroundDrawable(mygad);
//                        btn_Confirm.setTextColor(Color.WHITE);
//                        btn_Confirm.setBackgroundColor(bgcolor);

                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    public DateTimePickerControl(Context mContext, int theme, String mflag, String bgcolor) {
        super(mContext, theme);
        this.context = mContext;
        this.flag = mflag;
        this.bgcolor = Color.parseColor(bgcolor);
    }

    public DateTimePickerControl(Context context) {
        super(context);
        setContentView(R.layout.frame_dialog_datetimepicker);
        setCanceledOnTouchOutside(true);

    }

    public interface DateSelectListener {
        void DateSelect_Click(String Date);

    }

    public void SetDateSelectListener(DateSelectListener dateSelectListener) {
        m_DateSelectListener = dateSelectListener;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_Cancel) {
            DateTimePickerControl.this.dismiss();
        } else if (id == R.id.btn_Confirm) {
            if (m_DateSelectListener != null) {
                Calendar calendar = Calendar.getInstance();
                if (flag.equals("datetime")) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                            "yyyy/MM/dd HH:mm:ss");
                    calendar.set(dater.getYear(), dater.getMonth(),
                            dater.getDayOfMonth(), timer.getCurrentHour(),
                            timer.getCurrentMinute(), 0);
                    Date datetime = calendar.getTime();
                    m_DateSelectListener.DateSelect_Click(simpleDateFormat
                            .format(datetime));
                } else if (flag.equals("date")) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                            "yyyy/MM/dd");
                    calendar.set(dater.getYear(), dater.getMonth(),
                            dater.getDayOfMonth(), 0, 0, 0);
                    Date datetime = calendar.getTime();
                    m_DateSelectListener.DateSelect_Click(simpleDateFormat
                            .format(datetime));
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                            "HH:mm:ss");
                    calendar.set(0, 0,
                            0, timer.getCurrentHour(),
                            timer.getCurrentMinute(), 0);
                    Date datetime = calendar.getTime();
                    m_DateSelectListener.DateSelect_Click(simpleDateFormat
                            .format(datetime));
                }
            }
            DateTimePickerControl.this.dismiss();
        }
    }


    /**
     * 得到viewGroup里面的numberpicker组件
     *
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    /*
     * 调整numberpicker大小
     */
    private void resizeNumberPicker(NumberPicker np) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 0, 0, 0);
        np.setLayoutParams(params);
    }

    private void resizePikcer(FrameLayout tp) {
        List<NumberPicker> npList = findNumberPicker(tp);
        for (NumberPicker np : npList) {
            resizeNumberPicker(np);
        }
    }

}
