package com.example.tin.running;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.internal.view.menu.MenuView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tin.running.Service.ChronometerService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_stats.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_stats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_stats extends Fragment {

    private OnFragmentInteractionListener mListener;

    public TextView distanceData ;
    public TextView maxSpeedData ;
    public TextView avSpeedData ;
    public TextView currentSpeed ;
    public TextView chronometer ;
    public static Button stopRace ;
    public Button actualizar ;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment fragment_stats.
     */
    // TODO: Rename and change types and number of parameters




    public static fragment_stats newInstance(Bundle arguments) {
        fragment_stats fragment = new fragment_stats();

        if(arguments != null){
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    public fragment_stats() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment_stats, container, false);
        if (v != null){
            maxSpeedData = (TextView) getActivity().findViewById(R.id.top_speed_data);
            distanceData = (TextView) getActivity().findViewById(R.id.kilometers_traveled_data);
            currentSpeed = (TextView) getActivity().findViewById(R.id.current_speed_data);
            avSpeedData = (TextView) getActivity().findViewById(R.id.average_speed_data );
            chronometer = (TextView) getActivity().findViewById(R.id.chronometer2);
        }
        return v ;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null){
            savedInstanceState.putString("distanceData", distanceData.getText().toString());
            savedInstanceState.putString("maxSpeedData", maxSpeedData.getText().toString());
            savedInstanceState.putString("avSpeedData", avSpeedData.getText().toString());
            savedInstanceState.putString("currentSpeed", currentSpeed.getText().toString());
            savedInstanceState.putString("chronometer", chronometer.toString());
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(1);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    /*public static void startChronometer () {
        chronometer.start();
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.start_race){
            //empezar a correr el cronometro. y a tomar los datos de la carrera.
            chronometer = (Chronometer)getActivity().findViewById(R.id.chronometer);
            chronometer.start();
        }
        return super.onOptionsItemSelected(item);
    }*/
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if ( id == R.id.start_race){




            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer cArg) {
                    long time = SystemClock.elapsedRealtime() - cArg.getBase();
                    horaActual = (double) (time / 36000000);
                    int h = (int) (time / 3600000);
                    int m = (int) (time - h * 3600000) / 60000;
                    int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                    String hh = h < 10 ? "0" + h : h + "";
                    String mm = m < 10 ? "0" + m : m + "";
                    String ss = s < 10 ? "0" + s : s + "";
                    cArg.setText(hh + ":" + mm + ":" + ss);
                }
            });

            return true ;
        }

        return super.onOptionsItemSelected(item);
    }
        */

    @Override
    public void onStart() {
        super.onStart();
        maxSpeedData = (TextView) getActivity().findViewById(R.id.top_speed_data);
        distanceData = (TextView) getActivity().findViewById(R.id.kilometers_traveled_data);
        currentSpeed = (TextView) getActivity().findViewById(R.id.current_speed_data);
        avSpeedData = (TextView) getActivity().findViewById(R.id.average_speed_data);
        chronometer = (TextView) getActivity().findViewById(R.id.chronometer2);

        actualizar = (Button) getActivity().findViewById(R.id.actualizar);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mBoundChrono) {
                    chronometer.setText(MainActivity.mChronometerService.getFormatTime());
                }

                if (MainActivity.mBound && MainActivity.mBoundChrono) {

                    Double maxSpeed1 = MainActivity.mGPSService.getMaxSpeed() * (3.6);
                    maxSpeedData.setText(new DecimalFormat("#.#").format(maxSpeed1) + " Km/h");

                    Double distance2 = (MainActivity.mGPSService.getDistance() / 1000) ;
                    distanceData.setText(new DecimalFormat("#.###").format(distance2) + " Km");

                    Double currentSpeed2 = MainActivity.mGPSService.getCurrentSpeed() * (3.6);
                    currentSpeed.setText(new DecimalFormat("#.#").format(currentSpeed2) + " Km/h");

                    Double avSpeed = (Double)(distance2 / MainActivity.mChronometerService.getSeconds()*(10/36));
                    String a = avSpeed.toString();
                    //avSpeedData.setText(new DecimalFormat("#.#").format(avSpeed) + " Km/h");i
                    avSpeedData.setText (a);

                    chronometer.setText(MainActivity.mChronometerService.getFormatTime());
                }
            }
        });

        stopRace = (Button) getActivity().findViewById(R.id.stop_race);
        stopRace.setEnabled(false);
        stopRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mBoundChrono) {
                    chronometer.setText(MainActivity.mChronometerService.getFormatTime());
                }
                if (MainActivity.mBound) {
                    Toast.makeText(getActivity(),getString(R.string.finish_gps), Toast.LENGTH_SHORT)
                            .show();
                    getActivity().unbindService(MainActivity.mGPSServiceConnection);
                    MainActivity.mBound = false;
                }
                getActivity().unbindService(MainActivity.mChronoServiceConnection);
                MainActivity.raceOnStart = false;
                MainActivity.mBoundChrono = false;
                stopRace.setEnabled(false);

                SQLiteDatabase db = MainActivity.usdbh.getWritableDatabase();

                //Si hemos abierto correctamente la base de datos
                if (db != null) {

                    SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                    String fecha = s.format(new Date());

                    db.execSQL("INSERT INTO Stats (fecha, distancia, velMax, velPromedio, tiempo) " +
                            "VALUES ('" + fecha + "', '" + distanceData.getText() + "', '" + maxSpeedData.getText() + "', '" + avSpeedData.getText() + "', '" + chronometer.getText() + "')");

                    //Cerramos la base de datos
                    db.close();
                }
            }
        });


    }
/*
    @Override
    public void onResume() {
        super.onResume();



        if (MainActivity.mBound && MainActivity.mBoundChrono) {

            Double maxSpeed1 = MainActivity.mGPSService.getMaxSpeed() * (3.6);
            maxSpeedData.setText(maxSpeed1.toString() + " Km/h.");

            Double distance2 = MainActivity.mGPSService.getDistance() * (3.6);
            distanceData.setText(distance2.toString() + " Km.");

            Double currentSpeed2 = MainActivity.mGPSService.getCurrentSpeed();
            currentSpeed.setText(currentSpeed2.toString() + " Km/h.");

            Double avSpeed = distance2 / MainActivity.mChronometerService.getSeconds() * 3600;
            avSpeedData.setText(avSpeed.toString() + " Km/h.");

            chronometer.setText(MainActivity.mChronometerService.getFormatTime());
        }
    }
*/
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
            savedInstanceState.putString("distanceData", distanceData.getText().toString());
            savedInstanceState.putString("maxSpeedData", maxSpeedData.getText().toString());
            savedInstanceState.putString("avSpeedData", avSpeedData.getText().toString());
            savedInstanceState.putString("currentSpeed", currentSpeed.getText().toString());
            savedInstanceState.putString("chronometer", chronometer.toString());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            distanceData.setText(savedInstanceState.getString("distanceData"));
            maxSpeedData.setText(savedInstanceState.getString("maxSpeedData"));
            avSpeedData.setText(savedInstanceState.getString("avSpeedData"));
            currentSpeed.setText(savedInstanceState.getString("currentSpeed"));
            chronometer.setText(savedInstanceState.getString("chronometer"));
        }
    }

}
