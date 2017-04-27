package com.example.fedom.tapchallamge;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fedom.tapchallamge.ContentProvider.ScoreContentProvider;
import com.example.fedom.tapchallamge.Database.ScoreInfoTabel;
import com.example.fedom.tapchallamge.Service.RandomService;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_NAME_FOR_BUNDLE = "keycontatoreforbundle";
    boolean isPlaying=false;
    Button startButton;
    TextView textView;
    TextView textTap;
    String value;
    TextView nameToSave;
    int count;
    private MyAsyncTask asyncTask;
    private ServiceConnection serviceConnection;
    private RandomService randomService;
    private boolean isBindToService = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameToSave=(TextView)findViewById(R.id.textName);
        if (savedInstanceState != null) {
            nameToSave.setText(savedInstanceState.getString(KEY_NAME_FOR_BUNDLE));
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        ActionBarContainer actionBarContainer= (ActionBarContainer)findViewById(R.id.seeInfo);
        actionBarContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vIntent = new Intent(MainActivity.this, DetailScores.class);
                Bundle vBundle = new Bundle();
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
            }
        });

       serviceConnection= createServiceConnection();

        textView=(TextView)findViewById(R.id.counter);



        startButton = (Button) findViewById(R.id.startGameButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                startButton.setVisibility(View.GONE);
                asyncTask = new MyAsyncTask();
                asyncTask.execute();
                value=String.valueOf(randomService.getRandomNumber());

                nameToSave.setText(value);

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if(isPlaying) {
            textTap=(TextView)findViewById(R.id.tapCount);
            textTap.setText(""+count);
            int eventaction = event.getAction();
            if(eventaction==MotionEvent.ACTION_UP){
                count++;
                textTap.setText(""+count);
            }

        }else {
            startButton.setEnabled(true);
            startButton.setVisibility(View.VISIBLE);
            count=0;
        }
        return false;
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, String>{


        @Override
        protected String doInBackground(Void... params) {
            int i =3;
            count=0;
            try {
                while (i>0) {
                    publishProgress(i--);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isPlaying=false;
            ContentValues contentValues= new ContentValues();
            contentValues.put(ScoreInfoTabel.NAME,value);
            contentValues.put(ScoreInfoTabel.SCORE,String.valueOf(count));
            getContentResolver().insert(ScoreContentProvider.URI_SCORE,contentValues);
            return "FINISH";

        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText(String.valueOf(values[0]));
            isPlaying=true;

        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);


        }
    }


    private  ServiceConnection createServiceConnection(){
        return new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                RandomService.RandomServiceBinder binder = (RandomService.RandomServiceBinder) service;
                randomService = binder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                randomService = null;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.bindService(new Intent(MainActivity.this, RandomService.class), serviceConnection, BIND_AUTO_CREATE);
        this.isBindToService = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unbindService(serviceConnection);
        this.isBindToService = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NAME_FOR_BUNDLE,value);
    }
}