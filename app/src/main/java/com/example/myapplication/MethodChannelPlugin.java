package com.example.myapplication;

import android.app.Activity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.view.FlutterView;

/**
 * Created by Maker on 2019/7/26.
 * Description:
 */
public class MethodChannelPlugin implements MethodChannel.MethodCallHandler {

    private final FlutterActivity mFlutterActivity;
    private static String METHOD_CHANNEL = "MethodChannelPlugin";
    private  MethodChannel.Result mResult;

    public MethodChannelPlugin(FlutterActivity flutterActivity) {
        this.mFlutterActivity = flutterActivity;
    }

    static MethodChannelPlugin registerWith(FlutterActivity flutterActivity) {
        MethodChannel methodChannel = new MethodChannel(flutterActivity.registrarFor(METHOD_CHANNEL).messenger(), METHOD_CHANNEL);
        MethodChannelPlugin instance = new MethodChannelPlugin(flutterActivity);
        methodChannel.setMethodCallHandler(instance);
        return instance;
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        mResult = result;
        switch (methodCall.method) {
            case "message":
                showMessge((String) methodCall.arguments());
                result.success("MethodChannelPlugin收到消息：" + methodCall.arguments);
                break;
            default:
                result.notImplemented();
                break;
        }

    }

    public void sendMessage(String message){
        mResult.success(message);

    }

    private void showMessge(String message){
        if(mFlutterActivity instanceof IShowMessage){
            ((IShowMessage) mFlutterActivity).onShowEventMessage(message);
        }
        Toast.makeText(mFlutterActivity,message,Toast.LENGTH_SHORT).show();
    }
}
