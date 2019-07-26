package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import io.flutter.facade.Flutter;

public class MainActivity extends AppCompatActivity implements IShowMessage{
    private FrameLayout mFrameLayout;
    private TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout = findViewById(R.id.fl_flutter);
        mTv = findViewById(R.id.tv_test);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl_flutter, Flutter.createFragment("{message:'android2flutter'}"));
                ft.commit();

            }
        });
    }




    @Override
    public void onShowMessage(String message) {

        String s = "";

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

}
