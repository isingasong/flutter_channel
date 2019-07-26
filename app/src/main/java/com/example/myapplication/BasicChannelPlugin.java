package com.example.myapplication;

import android.app.Activity;
import android.widget.Toast;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.StringCodec;
import io.flutter.view.FlutterView;

/**
 * Created by Maker on 2019/7/26.
 * Description:
 */
public class BasicChannelPlugin implements BasicMessageChannel.MessageHandler<String>,BasicMessageChannel.Reply<String> {

    private final Activity mActivity;
    private final BasicMessageChannel<String> messageChannel;
    private final String BASIC_MESSAGE_CHANNEL_PLUGIN = "BasicMessageChannelPlugin";

    static BasicChannelPlugin registerWith(FlutterView flutterView){
        return new BasicChannelPlugin(flutterView);

    }


    public BasicChannelPlugin(FlutterView flutterView) {
        this.mActivity = (Activity) flutterView.getContext();
        this.messageChannel = new BasicMessageChannel<>(flutterView,BASIC_MESSAGE_CHANNEL_PLUGIN, StringCodec.INSTANCE);
        messageChannel.setMessageHandler(this);
    }


    @Override
    public void onMessage(String message, BasicMessageChannel.Reply<String> reply) {
        reply.reply("BasicMessageChannel收到Message：" + message);
        if(mActivity instanceof IShowMessage){
            ((IShowMessage) mActivity).onShowMessage(message);
        }
        Toast.makeText(mActivity,message,Toast.LENGTH_SHORT).show();

    }

    void send(String s, BasicMessageChannel.Reply<String> reply){
        messageChannel.send(s, reply);
    }

    @Override
    public void reply(String s) {

    }
}
