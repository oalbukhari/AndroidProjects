package com.example.fedom.tapchallamge.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by fedom on 23/02/2017.
 */

public class RandomService extends Service {
    private RandomServiceBinder randomServiceBinder = new RandomServiceBinder();
    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return randomServiceBinder;
    }
    public class RandomServiceBinder extends Binder {
        public RandomService getService() {
            return RandomService.this;
        }
    }
}
