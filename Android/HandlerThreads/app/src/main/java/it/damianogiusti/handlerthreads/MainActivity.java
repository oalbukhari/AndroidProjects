package it.damianogiusti.handlerthreads;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private WorkerThread workerThread;
    private Handler workerThreadHandler;
    private Handler mainThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workerThread = new WorkerThread("MyWorkerThread");
        workerThread.start();

        mainThreadHandler = new Handler(Looper.getMainLooper());
        workerThreadHandler = new Handler(workerThread.getLooper());

        workerThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, String.format("thread name: %s with priority == %d", Thread.currentThread().getName(), Thread.currentThread().getPriority()));
                onCompleted();
            }
        });
        Log.d(TAG, String.format("thread name: %s with priority == %d", Thread.currentThread().getName(), Thread.currentThread().getPriority()));
    }

    private void onCompleted() {
        Log.d(TAG, "onCompleted: thread name: " + Thread.currentThread().getName());
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onCompleted: thread name: " + Thread.currentThread().getName());
                workerThread.quit();
                Log.d(TAG, "onCompleted: workerThread quitted");
            }
        });
    }
}
