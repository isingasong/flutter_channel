package com.example.myapplication;

/**
 * Created by Maker on 2019/7/26.
 * Description:
 */
public interface IShowMessage {
    void onShowMessage(String message);
    void basicChannelSendMessage(String message);
    void eventChannelSendMessage(String message);
    void methodChannelSendMessage(String message);
}
