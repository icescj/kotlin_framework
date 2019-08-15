package com.sinoyd.Code.Until

import android.util.Log
import okhttp3.Call
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * 作者： scj
 * 创建时间： 2018/3/26
 * 版权：
 * 描述： com.sinoyd.Code.Until     基于xutils的Log显示静态类
 * */

class ShowLog4okhttp(var name: String) {
    val LINE_SEPARATOR = System.getProperty("line.separator")


    fun printinfo(response: Response): ShowLog4okhttp {
        Log.d(name, "#######################################分割线##########################################")
        Log.d(name, "Url:  " + response.request().url())
        Log.d(name, "Method:  " + response.request().method())
        Log.d(name, "Message:  " + response.message())
        Log.d(name, "Code:  " + response.code())
        Log.d(name, "Protocol:  " + response.protocol())
        Log.d(name, "ContentType:  " + response.body().contentType())
        return this
    }


    fun printinfo(call: Call) {
        Log.d(name, "######################################################################################")
        Log.d(name, "#######################################分割线##########################################")
        Log.d(name, "######################################################################################")
        Log.d(name, "Url:  " + call.request().url())
        Log.d(name, "Method:  " + call.request().method())
        Log.d(name, "Message:  " + "??")
        Log.d(name, "提示警告:  " + "请求出错,请联系服务器开发人员。")
    }


    fun printJson(msg: String, headString: String = "返回结果:") {
        var message: String
        try {
            if (msg.startsWith("{")) {
                var jsonObject: JSONObject = JSONObject(msg)
                message = jsonObject.toString(4)//最重要的方法,就一行,返回格式化的json字符串,其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                var jsonArray: JSONArray = JSONArray(msg);
                message = jsonArray.toString(4)
            } else {
                message = msg; }
        } catch (e: JSONException) {
            message = msg
        }
        printLine(true)
        message = headString + LINE_SEPARATOR + message
        var lines = message.split(LINE_SEPARATOR)
        for (line in lines) {
            Log.d(name, "║ $line")
        }
        printLine(false)
    }

    fun printLine(isTop: Boolean) {
        if (isTop) {
            Log.d(name, "╔═══════════════════════════════════════════════════════════════════════════════════════")
        } else {
            Log.d(name, "╚═══════════════════════════════════════════════════════════════════════════════════════")
        }
    }
}