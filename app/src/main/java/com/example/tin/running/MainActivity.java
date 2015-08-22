package com.example.tin.running;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tin.running.Service.ChronometerService;
import com.example.tin.running.Service.GPSService;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private int contGPSStart = 0;

    public static GPSService mGPSService;

    public static boolean raceOnStart = false ;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Bundle state = new Bundle();
    private fragment_stats  fragmentsStats = fragment_stats.newInstance(state);
    private MapFragment fragmentMap = MapFragment.newInstance("null","null");
    private DataFragment fragmentData = DataFragment.newInstance(null, null);

    // Creacion del ChronnometerServiceConnection
    public static boolean mBoundChrono;
    public ServiceConnection mChronoServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ChronometerService.ChronometerBinder mChronometerBinder = (ChronometerService.ChronometerBinder) service;
            mChronometerService = mChronometerBinder.getService();
            mBoundChrono = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    public static ChronometerService mChronometerService ;

    public ChronometerService getmChronometerService (){
        return mChronometerService ;
    }


    // Creación del GPSServiceConnection
    public static boolean mBound;
    public ServiceConnection mGPSServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GPSService.GPSBinder mGPSBinder = (GPSService.GPSBinder) service;
            mGPSService = mGPSBinder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        // Comportamiento del navegador.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (position == 0) {
           fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentsStats)
                    .commit();

        }

        if (position == 1) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentMap)
                    .commit();
        }

        if (position == 2){
            fragmentManager.beginTransaction().replace(R.id.container, fragmentData).commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Intent intent = new Intent(this, GPSService.class);
        if (id == R.id.start_gps) {
            contGPSStart++;// hay que guardar este valor
            if (contGPSStart % 2 == 1) {
                Toast.makeText(this, getString(R.string.iniciate_gps), Toast.LENGTH_SHORT)
                        .show();
                //Se inicia el servicio de GPS startService(intent);
                bindService(intent, mGPSServiceConnection, Context.BIND_AUTO_CREATE);
                mBound = true ;
            } else {
                if (mBound) {
                    Toast.makeText(this, "el servicio termino", Toast.LENGTH_SHORT)
                            .show();
                    unbindService(mGPSServiceConnection);
                    mBound = false;
                }
            }
        }
        if (id == R.id.exit)
            finish();
        Intent intentChrono = new Intent (this, ChronometerService.class);
        if (id==R.id.start_race){
            Toast.makeText(this, "Se inicia la carrera", Toast.LENGTH_SHORT)
                    .show();
            bindService (intentChrono , mChronoServiceConnection, Context.BIND_AUTO_CREATE);
            raceOnStart = true ;
            mBoundChrono = true ;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


    public GPSService getmGPSService (){
        return mGPSService ;
    }

}
