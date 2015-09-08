package com.example.tin.running.Threads;

import com.example.tin.running.MainActivity;
import com.example.tin.running.fragment_stats;

/**
 * Created by leo on 08/09/2015.
 */
public class ThreadChrono extends Thread{
    HandlerChrono handler ;

    public ThreadChrono(){
        handler = new HandlerChrono(fragment_stats.chronometer);
    }

    @Override
    public void run() {
        while (MainActivity.mBoundChrono) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.setCron(MainActivity.mChronometerService.getFormatTime());
            handler.act();
        }
    }
}
