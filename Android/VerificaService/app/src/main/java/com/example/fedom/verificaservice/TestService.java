package com.example.fedom.verificaservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by fedom on 27/01/2017.
 */

public class TestService extends Service {

    private static final String Tag_Service="TAG_SERVICE";
    MyBinder myBinder;




    @Override
    public void onDestroy() {
        super.onDestroy();


        Log.d(Tag_Service,"ServiceonDestroy");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Tag_Service,"ServiceonStart");

        Bundle vBundle = intent.getExtras();
        if(vBundle != null){
            String vstrign = vBundle.getString("Key1");
            Log.d(Tag_Service,"Bundle: "+vstrign);
        }




        return super.onStartCommand(intent, flags, startId);
    }

    private void timeLog(){
        try{
            for(int v=0;v<500;v++){
                Thread.sleep(100);
                Log.d("","lol"+v);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    BroadcastReceiver mbr2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            timeLog();
        }
    };



    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Tag_Service,"ServiceonCreate");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Tag_Service,"ServiceonBind");

        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public TestService getService(){
            return TestService.this;
        }

    }

}
