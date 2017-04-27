package it.damianogiusti.smsscherzo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageReceiverActivity extends AppCompatActivity {

    private static final String MESSAGE_KEY_FOR_BUNDLE = "messageBundled";

    private TextView txtMessageReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_receiver);
        bind();

        if (getIntent() != null)
            onIntent(getIntent());

    }

    private void bind() {
        txtMessageReceived = (TextView) findViewById(R.id.txtMessageReceived);
    }

    private void onIntent(@NonNull Intent intent) {
        if (intent.getExtras() != null) {
            String text = String.valueOf(intent.getExtras().get(Intent.EXTRA_TEXT));
            txtMessageReceived.setText(reverse(text));
        }
    }

    private String reverse(String inputString) {
        if (inputString.length() == 0)
            return "";

        return String.valueOf(inputString.charAt(inputString.length() - 1)) +
                reverse(inputString.substring(0, inputString.length() - 1));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MESSAGE_KEY_FOR_BUNDLE, txtMessageReceived.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtMessageReceived.setText(savedInstanceState.getString(MESSAGE_KEY_FOR_BUNDLE));
    }
}
