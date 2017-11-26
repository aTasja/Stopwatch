package com.ashatova.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.TextView;
import android.util.Log;

public class StopwatchActivity extends Activity {

    private int seconds = 0;
    private boolean running;
    //private final static String LOG_TAG = "StopwatchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("===onCreate===", "===timer is onCreate===");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null){
            Log.d("===Restart===", "===savedInastanceState is restart===");
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    // Запустить секундомер при щелчке на кнопке Start
    public void onClickStart(View view){
        Log.d("===Start===", "===button START pressed===");
        running = true;
    }
    // Остановить секундомер при щелчке на кнопке Stop
    public void onClickStop(View view){
        Log.d("===Stop===", "===button STOP pressed===");
        running = false;
    }
    //Обнулить секундомер при щелачке на кнопке Reset
    public void onClickReset(View view){
        Log.d("===Reset===", "===button RESET pressed===");
        running = false;
        seconds = 0;
    }
    //Обновление показаний таймера
    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = seconds / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


}