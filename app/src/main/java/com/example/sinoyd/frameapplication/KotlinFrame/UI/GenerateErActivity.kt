package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.sinoyd.frameapplication.KotlinFrame.Uitl.CodeCreator4Colour
import com.example.sinoyd.frameapplication.R
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_generate_er.*
import org.jetbrains.anko.onClick


class GenerateErActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_er)

        play.onClick {
            Log.i("scj", et_play.text.toString())
//            val bitmap = ZxingUtils.createBitmap(et_play.text.toString())
//            ivplay.setImageBitmap(bitmap)
        }


        play2.onClick {
            try {
                /*
                    * contentEtString：字符串内容
                    * w：图片的宽
                    * h：图片的高
                    * logo：不需要logo的话直接传null
                    * */

                val logo = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                var bitmap = CodeCreator4Colour.createQRCode(et_play.text.toString(), 400, 400, null)
                ivplay.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                e.printStackTrace()
            }

        }


    }
}
