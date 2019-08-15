package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.brightfuture.basemodule.Utils.Getpermission
import com.example.sinoyd.frameapplication.KotlinFrame.Uitl.PhotoUntil
import com.example.sinoyd.frameapplication.R
import com.rf.deskrobot.facemodule.utils.FaceModuleUtil
import kotlinx.android.synthetic.main.activity_photo.*
import org.jetbrains.anko.act
import org.jetbrains.anko.onClick
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File


//拍照，人脸注册
class PhotoActivity : AppCompatActivity() {
    val TAKE_PICTURE = 0x000001
    var photoUrl = Environment.getExternalStorageDirectory().toString() + "/Robot"
    var uri: Uri? = null
    var tempFile: File? = null
    var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        Getpermission.requestPermission(this)
        setlisteners()
    }

    private fun setlisteners() {
        //拍照
        bt_photo.onClick {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val picname = System.currentTimeMillis().toString() + ".jpg"
            tempFile = File(photoUrl, picname)
            uri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                Uri.fromFile(tempFile)
            } else {
                FileProvider.getUriForFile(act, act.packageName + ".provider", tempFile!!)
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(intent, TAKE_PICTURE)
        }

        //注册人脸
        bt_yanz.onClick {
            tvresult.text = ""
            multiThread()
        }
        //识别
        bt_jians.onClick {
            tvresult2.text = ""
            multiThread2()
        }
        //删除
        bt_del.onClick {
            tvresult3.text = ""
            multiThread3()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        //相机照片
        if (requestCode == Activity.DEFAULT_KEYS_DIALER || requestCode == TAKE_PICTURE) {
            try {
                if (!tempFile!!.exists()) {
                    tempFile!!.mkdirs()
                }
                //获取bitmap
                bitmap = PhotoUntil.getBitmapFormUri(act, uri!!)
                //存储bitmap
                PhotoUntil.saveMyBitmap(bitmap!!, tempFile!!)
                //显示bitmap
                ivpic.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun multiThread() {

        Observable.create(Observable.OnSubscribe<String> { t ->
            //子线程
            Log.e("scj", "===create: " + Thread.currentThread().name)
            t!!.onNext(FaceModuleUtil.RegisterFace(tempFile, "小明", "111111", "111111"))
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : Subscriber<String>() {
                            override fun onNext(t: String?) {
                                Log.e("scj", "===onNext: " + Thread.currentThread().name)
                                Log.e("scj", t)
                                tvresult.text = t
                            }

                            override fun onCompleted() {
                            }

                            override fun onError(e: Throwable?) {
                            }

                        }
                )
    }

    private fun multiThread2() {

        Observable.create(Observable.OnSubscribe<String> { t ->
            //子线程
            Log.e("scj", "===create: " + Thread.currentThread().name)
            t!!.onNext(FaceModuleUtil.searchFace(tempFile, "111111"))
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : Subscriber<String>() {
                            override fun onNext(t: String?) {
                                Log.e("scj", "===onNext: " + Thread.currentThread().name)
                                Log.e("scj", t)
                                tvresult2.text = t
                            }

                            override fun onCompleted() {
                            }

                            override fun onError(e: Throwable?) {
                            }

                        }
                )
    }

    private fun multiThread3() {

        Observable.create(Observable.OnSubscribe<String> { t ->
            //子线程
            Log.e("scj", "===create: " + Thread.currentThread().name)
            t!!.onNext(FaceModuleUtil.removeFace("111111", "839c72f21fc910e681d2676a9c5e73b4"))
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : Subscriber<String>() {
                            override fun onNext(t: String?) {
                                Log.e("scj", "===onNext: " + Thread.currentThread().name)
                                Log.e("scj", t)
                                tvresult3.text = t
                            }

                            override fun onCompleted() {
                            }

                            override fun onError(e: Throwable?) {
                            }

                        }
                )
    }

}
