package com.example.sinoyd.frameapplication.KotlinFrame.Uitl

import android.content.Intent
import android.net.Uri

import java.io.File

/**
 * Created by Sinoyd on 2017/12/13.
 * 作用：用于第三方软件打开文件
 */

object OpenFiles {
    //android获取一个用于打开HTML文件的intent
    fun getHtmlFileIntent(Path: String): Intent {
        val file = File(Path)
        val uri = Uri.parse(file.toString()).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(file.toString()).build()
        val intent = Intent("android.intent.action.VIEW")
        intent.setDataAndType(uri, "text/html")
        return intent
    }

    //android获取一个用于打开图片文件的intent
    fun getImageFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "image/*")
        return intent
    }

    //android获取一个用于打开PDF文件的intent
    fun getPdfFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "application/pdf")
        return intent
    }

    //android获取一个用于打开文本文件的intent
    fun getTextFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "text/plain")
        return intent
    }

    //android获取一个用于打开音频文件的intent
    fun getAudioFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("oneshot", 0)
        intent.putExtra("configchange", 0)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "audio/*")
        return intent
    }

    //android获取一个用于打开视频文件的intent
    fun getVideoFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("oneshot", 0)
        intent.putExtra("configchange", 0)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "video/*")
        return intent
    }


    //android获取一个用于打开CHM文件的intent
    fun getChmFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "application/x-chm")
        return intent
    }


    //android获取一个用于打开Word文件的intent
    fun getWordFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "application/msword")
        return intent
    }

    //android获取一个用于打开Excel文件的intent
    fun getExcelFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "application/vnd.ms-excel")
        return intent
    }

    //android获取一个用于打开PPT文件的intent
    fun getPPTFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent("android.intent.action.VIEW")
        intent.addCategory("android.intent.category.DEFAULT")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint")
        return intent
    }

    //android获取一个用于打开apk文件的intent
    fun getApkFileIntent(Path: String): Intent {
        val file = File(Path)
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
        return intent
    }
}
