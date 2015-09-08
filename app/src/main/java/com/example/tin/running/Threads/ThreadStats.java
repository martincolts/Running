package com.example.tin.running.Threads;

import com.example.tin.running.MainActivity;
import com.example.tin.running.fragment_stats;

import java.text.DecimalFormat;

/**
 * Created by leo on 08/09/2015.
 */
public class ThreadStats  extends Thread{


        public void run() {
            while (MainActivity.raceOnStart && MainActivity.mBoundChrono && MainActivity.mBound) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Double maxSpeed1 = MainActivity.mGPSService.getMaxSpeed() * (3.6);
                fragment_stats.maxSpeedData.setText(new DecimalFormat("#.#").format(maxSpeed1) + " Km/h");

                Double distance2 = (MainActivity.mGPSService.getDistance() / 1000);
                fragment_stats.distanceData.setText(new DecimalFormat("#.###").format(distance2) + " Km");

                Double currentSpeed2 = MainActivity.mGPSService.getCurrentSpeed() * (3.6);
                fragment_stats.currentSpeed.setText(new DecimalFormat("#.#").format(currentSpeed2) + " Km/h");

                Double avSpeed = new Double(MainActivity.mGPSService.getDistance() * 3.6 / MainActivity.mChronometerService.getSeconds());
                fragment_stats.avSpeedData.setText(new DecimalFormat("#.#").format(avSpeed) + " Km/h");
            }
        }
}
