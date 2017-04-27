package it.damianogiusti.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by damiano on 28/10/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String CREATION_DATE_KEY_FOR_BUNDLE = "creationDateBundled";

    @NonNull
    protected ActionBar supportActionBar;

    protected Button btnA;
    protected Button btnB;
    protected Button btnC;
    protected Button btnD;
    protected Button btnMain;
    protected Button btnNotify;

    protected TextView txt1;
    protected TextView txt2;

    private String creationDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            creationDate = new Date().toString();
        } else {
            creationDate = savedInstanceState.getString(CREATION_DATE_KEY_FOR_BUNDLE);
        }

        supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(getName());
        setupGUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CREATION_DATE_KEY_FOR_BUNDLE, creationDate);
    }

    protected abstract String getName();

    private void setupGUI() {
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);
        btnMain = (Button) findViewById(R.id.btnMain);
        btnNotify = (Button) findViewById(R.id.btnNotifica);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);

        txt1.setText(creationDate);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLaunchA();
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLaunchB();
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLaunchC();
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLaunchD();
            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMainClicked();
            }
        });
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNotifyClicked();
            }
        });
    }

    protected void onLaunchA() {
        Intent intent = new Intent(this, ActivityA.class);
        // l'activity se è gia in cima allo stack non verrà lanciata, ma verrà chiamato l'onNewIntent()
        // intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void onLaunchB() {
        Intent intent = new Intent(this, ActivityB.class);
        // l'activity verrà lanciata in un nuovo task, identificato dall'affinity specificato nel manifest
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void onLaunchC() {
        Intent intent = new Intent(this, ActivityC.class);

        startActivity(intent);
    }

    protected void onLaunchD() {
        Intent intent = new Intent(this, ActivityD.class);

        startActivity(intent);
    }

    protected void onMainClicked() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    protected void onNotifyClicked() {
        Intent intent = new Intent(this, ActivityA.class);

        startActivity(intent);
    }
}
