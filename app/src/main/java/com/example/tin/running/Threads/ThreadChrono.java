package com.example.tin.running.Threads;

import com.example.tin.running.MainActivity;
import com.example.tin.running.fragment_stats;

/**
 * Created by leo on 08/09/2015.
 */
public class ThreadChrono extends Thread{
    @Override
    public void run() {
        while (MainActivity.mBoundChrono) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fragment_stats.chronometer.setText(MainActivity.mChronometerService.getFormatTime());
        }
    }
}
