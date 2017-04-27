package it.damianogiusti.threads;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String FRAGMENT_TAG = "TimerFragment";

    private TextView textView;
    private Button btnStart;
    private Button btnCancel;

    private MyAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClicked();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask.cancel(true);
            }
        });

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null)
            getSupportFragmentManager();

    }

    private void bindViews() {
        textView = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.btnStartTimer);
        btnCancel = (Button) findViewById(R.id.btnCancelTimer);
    }

    private void onButtonClicked() {
//        textView.setText("Started");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                performTimeConsumingOperation();
//            }
//        }).start();
//        textView.setText("Completed");
        asyncTask = new MyAsyncTask();
        asyncTask.execute();
    }

    @WorkerThread
    private void performTimeConsumingOperation() {
        try {
            for (int i = 0; i < 100; i++) {

                final int counter = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("" + counter);
                    }
                });

                Log.d(TAG, "counter: " + i);
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            int i = 0;
            try {
                while (!isCancelled()) {
                    publishProgress(i++);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Completed";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }
    }
}
