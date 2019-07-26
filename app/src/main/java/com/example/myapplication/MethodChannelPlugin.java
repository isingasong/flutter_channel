package com.example.myapplication;

import android.app.Activity;
import android.widget.Toast;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

/**
 * Created by Maker on 2019/7/26.
 * Description:
 */
public class MethodChannelPlugin implements MethodChannel.MethodCallHandler {

    private final Activity mActivity;
    private static String METHOD_CHANNEL = "MethodChannelPlugin";

    public MethodChannelPlugin(Activity activity) {
        this.mActivity = activity;
    }

    public static void registerWith(FlutterView flutterView) {
        MethodChannel methodChannel = new MethodChannel(flutterView, METHOD_CHANNEL);
        MethodChannelPlugin instance = new MethodChannelPlugin((Activity) flutterView.getContext());
        methodChannel.setMethodCallHandler(instance);
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case "":
                break;
            default:
                break;
        }

    }

    private void showMessge(String message){
        if(mActivity instanceof IShowMessage){
            ((IShowMessage) mActivity).onShowMessage(message);
        }
        Toast.makeText(mActivity,message,Toast.LENGTH_SHORT).show();
    }
}
