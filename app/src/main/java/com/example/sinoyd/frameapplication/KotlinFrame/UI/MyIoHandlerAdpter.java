package com.example.sinoyd.frameapplication.KotlinFrame.UI;

import android.util.Log;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 作者： scj
 * 创建时间： 2018/7/16
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.UI
 */
public class MyIoHandlerAdpter extends IoHandlerAdapter {
    public final String TAG = "mina";

    public MyIoHandlerAdpter() {
        super();
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        Log.d(TAG, "sessionCreated: ");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        Log.d(TAG, "sessionOpened: ");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        Log.d(TAG, "sessionClosed: ");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        Log.d(TAG, "sessionIdle: ");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = (String) message.toString();
        Log.d(TAG, "messageReceived: " + msg);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        Log.d(TAG, "messageSent: ");
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        Log.d(TAG, "inputClosed: ");
    }
}
