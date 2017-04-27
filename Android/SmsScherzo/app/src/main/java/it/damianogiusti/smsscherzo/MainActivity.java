package it.damianogiusti.smsscherzo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_KEY_FOR_BUNDLE = "messageBundled";

    private EditText txtText;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtText = (EditText) findViewById(R.id.txtText);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(txtText.getText())) {
                    sendMessage(txtText.getText());
                } else
                    Toast.makeText(MainActivity.this, "Input a valid text to send.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMessage(CharSequence text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else Toast.makeText(this, "Install this app (?)", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MESSAGE_KEY_FOR_BUNDLE, txtText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtText.setText(savedInstanceState.getString(MESSAGE_KEY_FOR_BUNDLE));
    }
}
