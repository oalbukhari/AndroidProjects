package com.example.fedom.verificaservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String Tag_one="ONE";
    private int mCounter=0;

    private TextView text;
    boolean mbound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)findViewById(R.id.text_edit);
        Button btn_one= (Button) findViewById(R.id.button_pressed_one);
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("click"+mCounter++);
            }
        });
        Button btn_two= (Button)findViewById(R.id.button_pressed_two);
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(new Intent("START_LONG"));
            }
        });


    }


    private ServiceConnection nConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(Tag_one,"Connected");
            mbound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(Tag_one,"Disconnected");
        }
    };



Intent intentStartService;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag_one,"ActivityonStart");
        //bindService( new Intent(this,TestService.class), nConnection, Context.BIND_AUTO_CREATE);



        Bundle vBundle = new Bundle();
        vBundle.putString("KEY1","ciao sertvice");

        intentStartService= new Intent(this,TestIntentService.class);
        startService(intentStartService);

    }

    @Override
    protected void onStop() {
        super.onStop();
      /*  if(mbound){
            unbindService(nConnection);
            mbound=false;
        }*/

        stopService(intentStartService);

        Log.d(Tag_one,"ActivityonStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag_one,"ActivityOnDestroy");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag_one,"ActivityOnPause");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag_one,"ActivityOnResume");

    }
}
