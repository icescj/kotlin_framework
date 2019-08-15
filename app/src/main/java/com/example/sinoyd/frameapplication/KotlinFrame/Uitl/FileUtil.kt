package com.example.sinoyd.frameapplication.KotlinFrame.Uitl

import android.content.Context
import android.os.Environment
import android.util.Log

import java.io.File
import java.io.IOException
import java.sql.Timestamp
import java.text.DecimalFormat

/**
 * 文件工具类 Copyright (c) 2015
 *
 * @类型名称：FileUtil
 * @创建人：
 * @创建日期：
 * @维护人员：
 * @维护日期：
 * @功能摘要：
 */
object FileUtil {
    /**
     * 获取目录名称
     *
     * @param url
     * @return FileName
     */
    fun getFileName(url: String): String {
        val lastIndexStart = url.lastIndexOf("/")
        return if (lastIndexStart != -1) {
            url.substring(lastIndexStart + 1, url.length)
        } else {
            Timestamp(System.currentTimeMillis()).toString()
        }
    }

    /**
     * 判断SD卡是否存在
     *
     * @return boolean
     */
    fun checkSDCard(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    /**
     * 保存目录目录到目录
     *
     * @param context
     * @return 目录保存的目录
     */
    fun setMkdir(context: Context): String {
        var filePath: String? = null
        if (checkSDCard()) {
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "sinoyd" + File.separator + "downloads"
        } else {
            filePath = context.cacheDir.absolutePath + File.separator + "sinoyd" + File.separator + "downloads"
        }
        val file = File(filePath)
        if (!file.exists()) {
            file.mkdirs()
            Log.e("file", "目录不存在   创建目录    ")
        } else {
            Log.e("file", "目录存在")
        }
        return filePath
    }

    /**
     * 保存目录目录到目录
     *
     * @param context
     * @return 目录保存的目录
     */
    fun setHiddenMkdir(context: Context): String {
        var filePath: String? = null
        if (checkSDCard()) {
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "sinoyd" + File.separator + ".face"
        } else {
            filePath = context.cacheDir.absolutePath + File.separator + "sinoyd" + File.separator + ".face"
        }
        val file = File(filePath)
        if (!file.exists()) {
            file.mkdirs()
            Log.e("file", "目录不存在   创建目录    ")
        } else {
            Log.e("file", "目录存在")
        }
        return filePath
    }

    /**
     * 获取路径
     *
     * @return
     * @throws IOException
     */
    fun getPath(context: Context, url: String): String {
        var path: String = ""
        try {
            path = FileUtil.setMkdir(context) + File.separator + url.substring(url.lastIndexOf("/") + 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return path
    }

    /**
     * 获取文件的大小
     *
     * @param fileSize 文件的大小
     * @return
     */
    fun FormetFileSize(fileSize: Int): String {// 转换文件大小
        val df = DecimalFormat("#.00")
        var fileSizeString = ""
        if (fileSize < 1024) {
            fileSizeString = df.format(fileSize.toDouble()) + "B"
        } else if (fileSize < 1048576) {
            fileSizeString = df.format(fileSize.toDouble() / 1024) + "K"
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format(fileSize.toDouble() / 1048576) + "M"
        } else {
            fileSizeString = df.format(fileSize.toDouble() / 1073741824) + "G"
        }
        return fileSizeString
    }
}
