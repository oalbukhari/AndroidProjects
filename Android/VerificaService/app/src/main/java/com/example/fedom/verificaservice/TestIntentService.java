package com.example.fedom.verificaservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by fedom on 27/01/2017.
 */

public class TestIntentService extends IntentService {
    private static final String Tag_Service="TAG_SERVICE";

    public TestIntentService(){
        super("TestIntentService");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Tag_Service,"onUnbind");



        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(Tag_Service,"onDestroy");

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Tag_Service,"onBind");

        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Tag_Service,"onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d(Tag_Service,"onCreate");

        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(Tag_Service,"onHandleIntent");
        timeLog();

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
}
