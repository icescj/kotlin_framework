package com.example.sinoyd.frameapplication.KotlinFrame.Uitl.http

import com.example.sinoyd.frameapplication.KotlinFrame.Mode.Beaninfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


/**
 * Created by kaka on 2018/3/14.
 * email:375120706@qq.com
 */

interface ApiService {

    companion object {
        val BASEURL = "http://192.168.1.44:8080"
    }

    @FormUrlEncoded
    @POST("/deskrobot/get_user_info")
    fun postinfo(@Field("user_name") user_name: String): Observable<Beaninfo>






}
