package com.example.tin.running.Threads;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by leo on 24/09/2015.
 */
public class HandlerStats extends Handler{
    String ms, d, cs, avgs;
    TextView maxSpeed, distance, currentSpeed, avgSpeed ;

    public HandlerStats(TextView maxSpeed, TextView distance, TextView currentSpeed, TextView avgSpeed) {
        this.maxSpeed = maxSpeed;
        this.distance = distance;
        this.currentSpeed = currentSpeed;
        this.avgSpeed = avgSpeed;
    }

    public void handleMessage(Message msg) {
        maxSpeed.setText(ms);
        distance.setText(d);
        currentSpeed.setText(cs);
        avgSpeed.setText(avgs);
    }

    public void setStats(String ms, String d, String cs, String avgs) {
        this.ms = ms;
        this.d = d;
        this.cs = cs;
        this.avgs = avgs;
    }

    public void act() {
        super.sendEmptyMessage(0);
    }

}
