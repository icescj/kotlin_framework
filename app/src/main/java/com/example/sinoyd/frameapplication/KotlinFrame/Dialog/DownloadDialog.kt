package com.example.sinoyd.frameapplication.KotlinFrame.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast


import com.example.sinoyd.frameapplication.KotlinFrame.Uitl.FileUtil
import com.example.sinoyd.frameapplication.R


import java.io.FileOutputStream
import java.net.URL
import java.net.URLDecoder

/***
 * dialog文件下载
 *
 * @author
 */
class DownloadDialog(private val mContext: Context, url: String) : Dialog(mContext) {
    /**
     * 下载监听
     */
    private var downloadListener: DownloadListener? = null
    private var bt: Button? = null
    /**
     * 下载进度条
     */
    private var pb: ProgressBar? = null
    /**
     * 下载过程中不能点击
     */
    private var downloadOk = false
    private var tv: TextView? = null
    /**
     * 下载的url
     */
    private var url: String? = null
    /***
     * 得到文件的路径
     *
     * @return
     */
    var filePath: String = ""
    /**
     * 文件大小
     */
    internal var fileSize = 0
    /**
     * 下载的大小
     */
    internal var downloadSize = 0
    /**
     * handler
     */
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                DOWNLOAD_PREPARE -> {
                    downloadListener!!.startDownload()
                    // Toast.makeText(mContext, "准备下载", Toast.LENGTH_SHORT).show();
                    pb!!.visibility = ProgressBar.VISIBLE
                    Log.e(TAG, "文件大小:" + fileSize)
                    pb!!.max = fileSize
                }
                DOWNLOAD_WORK -> {
                    Log.e(TAG, "已经下载:" + downloadSize)
                    pb!!.progress = downloadSize
                    val res = downloadSize * 100 / fileSize
                    tv!!.text = "已下载：$res%"
                    bt!!.text = FileUtil.FormetFileSize(downloadSize) + "/" + FileUtil.FormetFileSize(fileSize)
                }
                DOWNLOAD_OK -> {
                    downloadOk = true
                    bt!!.text = "下载完成显示图片"
                    downloadSize = 0
                    fileSize = 0
                    Toast.makeText(mContext, "下载成功", Toast.LENGTH_SHORT).show()
                    if (downloadListener != null)
                        downloadListener!!.endDownload(filePath)
                    cancel()
                }
                DOWNLOAD_ERROR -> {
                    downloadSize = 0
                    fileSize = 0
                    Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show()
                    cancel()
                }
            }
            super.handleMessage(msg)
        }
    }

    fun setDownloadListener(downloadListener: DownloadListener) {
        this.downloadListener = downloadListener
    }

    init {
        this.url = url + "?ver=" + System.currentTimeMillis()
        filePath = FileUtil.getPath(mContext, url)
    }

    override fun cancel() {
        super.cancel()
    }

    /**
     * 下载文件
     */
    private fun downloadFile() {
        try {
            val u = URL(url)
            val conn = u.openConnection()
            val isis = conn.getInputStream()
            fileSize = conn.contentLength
            if (fileSize < 1 || isis == null) {
                sendMessage(DOWNLOAD_ERROR)
            } else {
                sendMessage(DOWNLOAD_PREPARE)
                val fos = FileOutputStream(URLDecoder.decode(filePath, "utf-8"))
                val bytes = ByteArray(1024)
                var len = isis.read(bytes)
                while ((len) != -1) {
                    fos.write(bytes, 0, len)
                    fos.flush()
                    downloadSize += len
                    sendMessage(DOWNLOAD_WORK)
                }
                sendMessage(DOWNLOAD_OK)
                isis.close()
                fos.close()
            }
        } catch (e: Exception) {
            sendMessage(DOWNLOAD_ERROR)
            e.printStackTrace()
        }

    }

    private fun init() {
        bt = this.findViewById(R.id.down_bt)
        tv = this.findViewById(R.id.down_tv)
        pb = this.findViewById(R.id.down_pb)
    }

    private fun down() {
        // 启动一个线程下载文件
        val thread = Thread(Runnable { downloadFile() })
        thread.start()
    }

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_download_layuot)
        init()
    }

    /**
     * @param what
     */
    private fun sendMessage(what: Int) {
        val m = Message()
        m.what = what
        handler.sendMessage(m)
    }

    override fun show() {
        downloadOk = false
        super.show()
        down()
    }

    interface DownloadListener {
        /***
         * 开始下载
         */
        fun startDownload()

        /***
         * 下载完成
         *
         * @param filePath
         */
        fun endDownload(filePath: String)
    }

    companion object {
        /**
         * 准备下载变量
         */
        private val DOWNLOAD_PREPARE = 0
        /**
         * 正在下载变量
         */
        private val DOWNLOAD_WORK = 1
        /**
         * 下载完成变量
         */
        private val DOWNLOAD_OK = 2
        /**
         * 下载错误变量
         */
        private val DOWNLOAD_ERROR = 3
        private val TAG = "DownloadDialog"
    }
}
