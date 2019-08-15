package com.example.sinoyd.frameapplication.KotlinFrame.Adapter

import android.content.Context
import android.net.wifi.ScanResult
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sinoyd.frameapplication.R

/**
 * 作者： scj
 * 创建时间： 2018/3/8
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.environmentNT.adapter
 */


class WifiRecyclerViewAdapter(var context: Context, val datas: List<ScanResult>) : RecyclerView.Adapter<WifiRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wifi_manager, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datas[position]

        //设置wifi  ssid
        holder.wifissid.text = data.SSID


    }

    override fun getItemCount(): Int = datas.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var wifissid: TextView = view.findViewById<View>(R.id.tv_wifissid) as TextView
    }


}
