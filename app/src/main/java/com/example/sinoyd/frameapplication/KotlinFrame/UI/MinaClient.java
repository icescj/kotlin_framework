package com.example.sinoyd.frameapplication.KotlinFrame.UI;

import android.content.Context;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaClient {
    private static final MinaClient ourInstance = new MinaClient();
    private static Context mCtx;
    private IoConnector mConnector;

    public static MinaClient getInstance(Context ctx) {
        mCtx = ctx;
        return ourInstance;
    }

    private MinaClient() {
        mConnector = new NioSocketConnector();

    }

    public void initClient() {
        mConnector.setConnectTimeoutMillis(30000);
        mConnector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new TextLineCodecFactory(Charset.forName("UTF-8"),
                                LineDelimiter.WINDOWS.getValue(),
                                LineDelimiter.WINDOWS.getValue())
                )
        );

        mConnector.setHandler((IoHandler) mCtx);
        mConnector.connect(new InetSocketAddress("192.168.1.7", 9180));


    }
}
