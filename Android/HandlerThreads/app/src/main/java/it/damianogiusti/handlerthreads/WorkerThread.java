package it.damianogiusti.handlerthreads;

import android.os.HandlerThread;

/**
 * Created by Damiano Giusti on 21/11/16.
 */
public class WorkerThread extends HandlerThread {

    public WorkerThread(String name) {
        super(name);
    }
}
