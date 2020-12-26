package com.example.processcommunicationtest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {


        return  mBinder;
    }


    private final  UserAidl.Stub mBinder =new UserAidl.Stub() {
        @Override
        public String getUserName() throws RemoteException {
            return "game";
        }

        @Override
        public String getUerPwd() throws RemoteException {
            return "123456";
        }
    };
//    private class UserBinder implements UserAidl {
//
//
//
//        @Override
//        public IBinder asBinder() {
//            return null;
//        }
//
//        @Override
//        public String getUserName() throws RemoteException {
//            return "game";
//        }
//
//        @Override
//        public String getUerPwd() throws RemoteException {
//            return "123456";
//        }
//    }
}
