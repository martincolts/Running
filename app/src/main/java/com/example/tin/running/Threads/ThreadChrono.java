package com.example.tin.running.Threads;

import android.widget.Toast;

import com.example.tin.running.JavaClases.ServiceTools;
import com.example.tin.running.MainActivity;
import com.example.tin.running.R;
import com.example.tin.running.Service.ChronometerService;
import com.example.tin.running.fragment_stats;

/**
 * Created by leo on 08/09/2015.
 */
public class ThreadChrono extends Thread{
    HandlerChrono handler ;

    public ThreadChrono(HandlerChrono handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        while (MainActivity.mBoundChrono) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (MainActivity.mChronometerService != null) {
                handler.setCron("" + MainActivity.mChronometerService.getFormatTime());
                //handler.setCron(""+System.currentTimeMillis());
                handler.act();
            }
        }
    }
}
