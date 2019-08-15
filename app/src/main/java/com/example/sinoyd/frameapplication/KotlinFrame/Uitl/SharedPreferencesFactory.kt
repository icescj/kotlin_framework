package com.sinoyd.Code.Until

import android.content.Context

/**
 * Created by shenchuanjiang on 2017/2/26.
 *
 *
 * 使用SharedPreferences存取删  数据
 */

object SharedPreferencesFactory {
    //存数据
    fun savedata(context: Context, key: String, info: String) {

        // 1.获得SharedPreferences对象
        val sp = context.getSharedPreferences(key, Context.MODE_PRIVATE)// 私有，不能追加
        // 2.获得Editor对象
        val et = sp.edit()
        // 3.存储数据
        et.putString(key, info)
        // 4.提交数据，将数据写在文件
        et.commit()
    }

    //取数据
    fun getdata(context: Context, key: String): String {
        val sp = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        val token = sp.getString(key, "")
        return if ("" != token) {
            token
        } else {
            ""
        }
    }

    //清除数据
    fun cleardata(context: Context, key: String) {
        val sp = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        sp.edit().clear().commit()
    }
}
