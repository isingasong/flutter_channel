package com.example.myapplication;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.EventChannel;
import io.flutter.view.FlutterView;

/**
 * Created by Maker on 2019/7/26.
 * Description: 持续监听，常用于Native向dart发送数据，手机电量，网络变化，陀螺仪，传感器等
 * 收到消息无法回复
 */
public class EventChannelPlugin implements EventChannel.StreamHandler {
    private EventChannel.EventSink eventSink;
    private static String EVENT_CHANNEL = "EventChannelPlugin";

    static EventChannelPlugin registerWith(FlutterActivity flutterActivity){
        EventChannelPlugin eventChannelPlugin = new EventChannelPlugin();
        new EventChannel(flutterActivity.registrarFor(EVENT_CHANNEL).messenger(),EVENT_CHANNEL).setStreamHandler(eventChannelPlugin);
        return eventChannelPlugin ;
    }

    void send(Object params){
        if(eventSink != null){
            eventSink.success(params);
        }

    }

    @Override
    public void onListen(Object o, EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;

    }

    @Override
    public void onCancel(Object o) {
        eventSink = null;

    }
}
