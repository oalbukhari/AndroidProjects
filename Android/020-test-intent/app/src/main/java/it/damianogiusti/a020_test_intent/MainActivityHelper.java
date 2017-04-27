package it.damianogiusti.a020_test_intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by damiano on 04/11/16.
 */

public abstract class MainActivityHelper extends AppCompatActivity {

    protected static final String TAG = "MainActivityH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGUI();
    }

    private void setupGUI() {
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnSms = (Button) findViewById(R.id.btnSms);
        Button btnPick = (Button) findViewById(R.id.btnPick);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnWebPressed();
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnSmsPressed();
            }
        });
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnPickPressed();
            }
        });
    }

    protected abstract void onBtnWebPressed();

    protected abstract void onBtnSmsPressed();

    protected abstract void onBtnPickPressed();
}
