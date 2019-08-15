package com.example.sinoyd.frameapplication.KotlinFrame.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sinoyd.frameapplication.R;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class Mina2Activity extends AppCompatActivity {
    public final String TAG = "mina";
    private MyIoHandlerAdpter myIoHandlerAdpter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina2);


        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));
        myIoHandlerAdpter = new MyIoHandlerAdpter();
        connector.setHandler(myIoHandlerAdpter);
        connector.connect(new InetSocketAddress("192.168.1.7", 9180));
    }


}
