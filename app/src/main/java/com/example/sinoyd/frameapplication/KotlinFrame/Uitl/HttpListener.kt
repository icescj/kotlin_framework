package com.sinoyd.Code.Until

import okhttp3.Call
import okhttp3.Response


/**
 * 作者： scj
 * 创建时间： 2018/1/30
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Until
 */

interface HttpListener {
    /***
     * 请求网络成功
     *
     * @param resData
     */
    fun requestSuccess(response: Response, TAG: String)

    /***
     * 请求网络失败
     *
     * @param resData
     */
    fun requestFailed(response: Response)


    fun onFailure(call: Call)
}