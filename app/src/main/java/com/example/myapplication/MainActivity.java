package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import io.flutter.app.FlutterActivity;
import io.flutter.facade.Flutter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import io.flutter.plugin.common.BasicMessageChannel;


public class MainActivity extends FlutterActivity implements IShowMessage, View.OnClickListener {
    private FrameLayout mFrameLayout;
    private TextView mtvFlutter;
    private EditText mEtMethodMessageSend;
    private EditText mEtEventMessageSend;
    private EditText mEtBasicMessageSend;
    private EditText mEtMethodMessageGet;
    private EditText mEtEventMessageGet;
    private EditText mEtBasicMessageGet;
    private IShowMessage mIShowMessage;
    private MethodChannelPlugin methodChannelPlugin;
    private EventChannelPlugin eventChannelPlugin;
    private BasicChannelPlugin basicChannelPlugin;
    private String methodMessage;
    private String eventMessage;
    private String basicMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPlugin();

    }

    private void initPlugin() {
        methodChannelPlugin = MethodChannelPlugin.registerWith(this);
        eventChannelPlugin = EventChannelPlugin.registerWith(this);
        basicChannelPlugin = BasicChannelPlugin.registerWith(this);
    }

    private void initView() {
        mFrameLayout = findViewById(R.id.fl_flutter);
        mtvFlutter = findViewById(R.id.tv_flutter);
        mEtMethodMessageSend = findViewById(R.id.et_methodChannel_send);
        mEtEventMessageSend = findViewById(R.id.et_eventChannel_send);
        mEtBasicMessageSend = findViewById(R.id.et_basicChannel_send);
        mEtMethodMessageGet = findViewById(R.id.et_methodChannel_get);
        mEtEventMessageGet = findViewById(R.id.et_eventChannel_get);
        mEtBasicMessageGet = findViewById(R.id.et_basicChannel_get);
        mtvFlutter.setOnClickListener(this);


        mEtMethodMessageSend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                methodMessage = charSequence.toString();
                methodChannelPlugin.sendMessage(methodMessage);
//                mIShowMessage.methodChannelSendMessage(methodMessage);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEtEventMessageSend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                eventMessage = charSequence.toString();
                eventChannelPlugin.send(eventMessage);
//                mIShowMessage.eventChannelSendMessage(eventMessage);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEtBasicMessageSend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                basicMessage = charSequence.toString();
                basicChannelPlugin.send(basicMessage, null);
//                mIShowMessage.basicChannelSendMessage(basicMessage);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }



    @Override
    public void onShowBasicMessage(String message) {
        mEtBasicMessageGet.setText(message);


    }

    @Override
    public void onShowMethodMessage(String message) {
        mEtMethodMessageGet.setText(message);

    }

    @Override
    public void onShowEventMessage(String message) {
        mEtEventMessageGet.setText(message);

    }

    @Override
    public void basicChannelSendMessage(String message) {

    }

    @Override
    public void eventChannelSendMessage(String message) {

    }

    @Override
    public void methodChannelSendMessage(String message) {

    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fl_flutter,Flutter.createFragment("{message:'android2flutter'}"));
        ft.commit();

    }

}
