package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sinoyd.frameapplication.KotlinFrame.Mode.Beaninfo
import com.example.sinoyd.frameapplication.KotlinFrame.Uitl.http.ApiService
import com.example.sinoyd.frameapplication.KotlinFrame.Uitl.http.RetrofitManager
import com.example.sinoyd.frameapplication.R
import com.lkl.demo.https.HttpSimpleSubscriber
import kotlinx.android.synthetic.main.activity_network.*
import okhttp3.MediaType
import org.jetbrains.anko.onClick
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NetworkActivity : AppCompatActivity() {

    val JSON = MediaType.parse("application/json; charset=utf-8")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        btrequest.onClick {
            requestpsot()
        }

    }


    private fun requestpsot() {
        RetrofitManager.create(ApiService::class.java).postinfo("13324243358")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        object : HttpSimpleSubscriber<Beaninfo>() {
                            override fun _onNext(retData: Beaninfo?) {
                                var text = retData!!.`_$Msg136`.nickname
                                tvget.text = text
                            }
                        }
                )
    }

}
