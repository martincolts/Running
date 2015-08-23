package com.example.tin.running.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.tin.running.JavaClases.Chronometer;

public class ChronometerService extends Service {

    private Chronometer mChronometer = new Chronometer() ;

    public ChronometerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mChronometer.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mChronometer.start();
        return 1;
    }

    public String getFormatTime (){
        //mChronometer.stop();
        String res = mChronometer.getTimeFormated();
        //mChronometer.start();
        return res ;

    }

    public Double getSeconds (){
        return mChronometer.getSeconds();
    }

    @Override
    public void onDestroy() {
        mChronometer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder ;
    }

    public class ChronometerBinder extends Binder {
        public ChronometerService getService (){
            return ChronometerService.this ;
        }
    }

    private ChronometerBinder mBinder = new ChronometerBinder();
}
