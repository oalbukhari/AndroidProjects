package it.damianogiusti.threads;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by Damiano Giusti on 10/02/17.
 */
public class TimerFragment extends Fragment {

    public interface Callbacks {
        void onTimerUpdate(int count);
        void onTimerCanceled(int lastCount);
    }

    public static TimerFragment newInstance(Bundle bundle) {
        TimerFragment timerFragment = new TimerFragment();
        timerFragment.setArguments(bundle);
        return timerFragment;
    }


    private TimerFragment.Callbacks callbacks;
    private AsyncTimer asyncTimer;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof TimerFragment.Callbacks)
            callbacks = (TimerFragment.Callbacks) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);

        asyncTimer = new AsyncTimer(callbacks);
        asyncTimer.execute();

        return null;
    }

    public AsyncTimer getAsyncTimer() {
        return asyncTimer;
    }

    private static class AsyncTimer extends AsyncTask<Void, Integer, Void> {

        private WeakReference<TimerFragment.Callbacks> weakCallbacks;
        private int counter;

        public AsyncTimer(Callbacks callbacks) {
            this.weakCallbacks = new WeakReference<>(callbacks);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            counter = 0;
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (!isCancelled()) {
                publishProgress(counter++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TimerFragment.Callbacks callbacks = weakCallbacks.get();
            if (callbacks != null)
                callbacks.onTimerUpdate(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            TimerFragment.Callbacks callbacks = weakCallbacks.get();
            if (callbacks != null)
                callbacks.onTimerCanceled(counter);
        }
    }
}
