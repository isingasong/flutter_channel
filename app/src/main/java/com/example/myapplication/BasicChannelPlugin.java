package com.example.myapplication;

import android.app.Activity;
import android.widget.Toast;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.StringCodec;
import io.flutter.view.FlutterView;

/**
 * Created by Maker on 2019/7/26.
 * Description:
 */
public class BasicChannelPlugin implements BasicMessageChannel.MessageHandler<String>,BasicMessageChannel.Reply<String> {

    private final FlutterActivity mFlutterActivity;
    private final BasicMessageChannel<String> messageChannel;
    private final String BASIC_MESSAGE_CHANNEL_PLUGIN = "BasicMessageChannelPlugin";

    static BasicChannelPlugin registerWith(FlutterActivity flutterActivity){
        return new BasicChannelPlugin(flutterActivity);

    }


    public BasicChannelPlugin(FlutterActivity flutterActivity) {
        this.mFlutterActivity = flutterActivity;
        this.messageChannel = new BasicMessageChannel<>(flutterActivity.registrarFor(BASIC_MESSAGE_CHANNEL_PLUGIN).messenger(),BASIC_MESSAGE_CHANNEL_PLUGIN, StringCodec.INSTANCE);
        messageChannel.setMessageHandler(this);
    }


    @Override
    public void onMessage(String message, BasicMessageChannel.Reply<String> reply) {
        reply.reply("BasicMessageChannel收到Message：" + message);
        if(mFlutterActivity instanceof IShowMessage){
            ((IShowMessage) mFlutterActivity).onShowBasicMessage(message);
        }
        Toast.makeText(mFlutterActivity,message,Toast.LENGTH_SHORT).show();

    }

    void send(String s, BasicMessageChannel.Reply<String> reply){
        messageChannel.send(s, reply);
    }

    @Override
    public void reply(String s) {

    }
}
