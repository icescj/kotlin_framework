package com.example.sinoyd.frameapplication.KotlinFrame.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sinoyd.frameapplication.R


class MINAActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi)
//
//       IoConnector connector = new NioSocketConnector();
//        connector.setConnectTimeoutMillis(30000);
//        connector.getFilterChain().addLast("codec",
//                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
//                        LineDelimiter.WINDOWS.getValue(),
//                        LineDelimiter.WINDOWS.getValue())));
//
//        connector.setHandler(new TCPClientHandler("你好！\r\n 大家好！"));
//
//        connector.connect(new InetSocketAddress("192.168.1.10", 8090));

    }


}
