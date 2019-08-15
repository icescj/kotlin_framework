package com.sinoyd.Code.Until

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by shenchuanjiang on 2017/4/6.
 *
 *
 * android:singleLine="true"
 */

object DisplayorhideSoftkeyboard {
    /***显示软键盘 */
    fun dispalySoftkeyboard(et: EditText) {
        val imm = et.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED)
    }

    /***隐藏软键盘 */
    fun hideSoftkeyboard(context: Activity) {
        context.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

}
