package com.example.sinoyd.frameapplication.KotlinFrame.Uitl

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import java.io.ByteArrayOutputStream

/**
 * 作者： scj
 * 创建时间： 2018/4/10
 * 版权：
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.Uitl
 */

object Picturecompression {

    //压缩图片到适合到capacity大小
    fun CompressionBitmap(bmp: Bitmap, capacity: Int = 600): Bitmap {
        // 首先进行一次大范围的压缩
        val output = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, output)
        val zoom = Math.sqrt((capacity * 1024 / output.toByteArray().size.toFloat()).toDouble()).toFloat() //获取缩放比例
        // 设置矩阵数据
        val matrix = Matrix()
        matrix.setScale(zoom, zoom)
        // 根据矩阵数据进行新bitmap的创建
        var resultBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, matrix, true)
        output.reset()
        resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output)
        // 如果进行了上面的压缩后，依旧大于600K，就进行小范围的微调压缩
        while (output.toByteArray().size > capacity * 1024) {
            Log.i("capacity", resultBitmap.byteCount.toString() + "")
            matrix.setScale(0.9f, 0.9f)//每次缩小 1/10
            resultBitmap = Bitmap.createBitmap(
                    resultBitmap, 0, 0,
                    resultBitmap.width, resultBitmap.height, matrix, true)
            output.reset()
            resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output)
        }
        Log.i("capacity", resultBitmap.byteCount.toString() + "")
        return resultBitmap
    }
}