package com.example.clienttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.processcommunicationtest.UserAidl;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    String userName;
    String pw;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UserAidl userAidl = UserAidl.Stub.asInterface(service);
            try {
                userName = userAidl.getUserName();
                pw = userAidl.getUerPwd();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        y隐式调用其他的应用的服务
        Intent intent = new Intent();
        intent.setAction("com.example.MyService");
        intent.setPackage("com.example.processcommunicationtest");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


        tv = findViewById(R.id.tv);
        findViewById(R.id.btn_get_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.append("\n");
                tv.append(userName);
            }
        });
        findViewById(R.id.btn_get_pw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.append("\n");
                tv.append(pw);
            }
        });


    }
}
