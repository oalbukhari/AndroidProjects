package it.damianogiusti.task;

import android.content.Intent;
import android.util.Log;

/**
 * Created by damiano on 28/10/16.
 */
public class ActivityA extends BaseActivity {

    private static final String TAG = "ActivityA";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent");
    }

    @Override
    protected String getName() {
        return getClass().getSimpleName();
    }
}
