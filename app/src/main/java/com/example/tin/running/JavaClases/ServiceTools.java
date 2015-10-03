package com.example.tin.running.JavaClases;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * Created by leo on 24/09/2015.
 */
public class ServiceTools extends Activity{
    //private static String LOG_TAG = ServiceTools.class.getName();

    public ServiceTools(){

    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}