package com.example.tin.running;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.CameraUpdate;

public class MapFragment extends Fragment {


    private MapView mMapView ;
    private GoogleMap mGoogleMap ;

    private OnFragmentInteractionListener mListener;


    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MapFragment() {
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

        View v = inflater.inflate(R.layout.fragment_map, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately
    //agrego un comentario
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mGoogleMap = mMapView.getMap();
        mGoogleMap.setMyLocationEnabled(true);
/*
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {

                double lat = location.getLatitude();
                double lon = location.getLongitude();
                LatLng coordinate = new LatLng(lat, lon);
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 17);
                mGoogleMap.animateCamera(yourLocation);
                Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(lat, lon)).visible(true).icon(BitmapDescriptorFactory.fromAsset("redCircule.png"))
                );
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);*/
        return v ;
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
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(2);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.start_race){
            if (MainActivity.raceOnStart && MainActivity.mBound){
                actualizarMapa(this.mGoogleMap);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    public static void actualizarMapa (GoogleMap mGoogleMap) {

        LatLng coordinate = MainActivity.mGPSService.getCoordinate() ;
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 17);
        mGoogleMap.animateCamera(yourLocation);
        Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(coordinate).visible(true).icon(BitmapDescriptorFactory.fromAsset("redCircule.png"))
        );
    }

}
