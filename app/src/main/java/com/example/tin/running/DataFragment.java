package com.example.tin.running;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DataFragment extends Fragment {


    TableLayout tabla;
    TableLayout cabecera;
    TableRow.LayoutParams layoutFila;
    TableRow.LayoutParams layoutFecha;
    TableRow.LayoutParams layoutDistancia;
    TableRow.LayoutParams layoutVelMax;
    TableRow.LayoutParams layoutVelPromedio;
    TableRow.LayoutParams layoutTiempo;

    Resources rs;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(Bundle arguments) {
        DataFragment fragment = new DataFragment();

        if(arguments != null){
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_data, container, false);
        //super.getActivity().setContentView(R.layout.fragment_data);
        /*
        if (v != null) {
            rs = this.getResources();
            tabla = (TableLayout) super.getActivity().findViewById(R.id.tabla);
            cabecera = (TableLayout) super.getActivity().findViewById(R.id.cabecera);
            layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            layoutFecha = new TableRow.LayoutParams(270, TableRow.LayoutParams.WRAP_CONTENT);
            layoutDistancia = new TableRow.LayoutParams(180, TableRow.LayoutParams.WRAP_CONTENT);
            layoutVelMax = new TableRow.LayoutParams(160, TableRow.LayoutParams.WRAP_CONTENT);
            layoutVelPromedio = new TableRow.LayoutParams(220, TableRow.LayoutParams.WRAP_CONTENT);
            layoutTiempo = new TableRow.LayoutParams(220, TableRow.LayoutParams.WRAP_CONTENT);

            agregarCabecera();
            agregarFilasTabla();
        }
        */
        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        rs = this.getResources();
        tabla = (TableLayout) super.getActivity().findViewById(R.id.tabla);
        cabecera = (TableLayout) super.getActivity().findViewById(R.id.cabecera);
        layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        layoutFecha = new TableRow.LayoutParams(270, TableRow.LayoutParams.WRAP_CONTENT);
        layoutDistancia = new TableRow.LayoutParams(180, TableRow.LayoutParams.WRAP_CONTENT);
        layoutVelMax = new TableRow.LayoutParams(160, TableRow.LayoutParams.WRAP_CONTENT);
        layoutVelPromedio = new TableRow.LayoutParams(220, TableRow.LayoutParams.WRAP_CONTENT);
        layoutTiempo = new TableRow.LayoutParams(220, TableRow.LayoutParams.WRAP_CONTENT);

        agregarCabecera();
        agregarFilasTabla();
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
        ((MainActivity) activity).onSectionAttached(3);
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

    public void agregarCabecera(){
        TableRow fila;
        TextView txtFecha;
        TextView txtDistancia;
        TextView txtVelMax;
        TextView txtVelPromedio;
        TextView txtTiempo;

        fila = new TableRow(super.getActivity());
        fila.setLayoutParams(layoutFila);

        txtFecha = new TextView(super.getActivity());
        txtDistancia = new TextView(super.getActivity());
        txtVelMax = new TextView(super.getActivity());
        txtVelPromedio = new TextView(super.getActivity());
        txtTiempo = new TextView(super.getActivity());

        txtFecha.setText(rs.getString(R.string.fecha));
        txtFecha.setGravity(Gravity.CENTER_HORIZONTAL);
        txtFecha.setTextAppearance(super.getActivity(), R.style.etiqueta);
        txtFecha.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtFecha.setLayoutParams(layoutFecha);

        txtDistancia.setText(rs.getString(R.string.distancia));
        txtDistancia.setGravity(Gravity.CENTER_HORIZONTAL);
        txtDistancia.setTextAppearance(super.getActivity(), R.style.etiqueta);
        txtDistancia.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtDistancia.setLayoutParams(layoutDistancia);

        txtVelMax.setText(rs.getString(R.string.velMax));
        txtVelMax.setGravity(Gravity.CENTER_HORIZONTAL);
        txtVelMax.setTextAppearance(super.getActivity(), R.style.etiqueta);
        txtVelMax.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtVelMax.setLayoutParams(layoutVelMax);

        txtVelPromedio.setText(rs.getString(R.string.velPromedio));
        txtVelPromedio.setGravity(Gravity.CENTER_HORIZONTAL);
        txtVelPromedio.setTextAppearance(super.getActivity(), R.style.etiqueta);
        txtVelPromedio.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtVelPromedio.setLayoutParams(layoutVelPromedio);

        txtTiempo.setText(rs.getString(R.string.tiempo));
        txtTiempo.setGravity(Gravity.CENTER_HORIZONTAL);
        txtTiempo.setTextAppearance(super.getActivity(), R.style.etiqueta);
        txtTiempo.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtTiempo.setLayoutParams(layoutTiempo);

        fila.addView(txtFecha);
        fila.addView(txtDistancia);
        fila.addView(txtVelMax);
        fila.addView(txtVelPromedio);
        fila.addView(txtTiempo);

        cabecera.addView(fila);
    }

    public void agregarFilasTabla(){

        TableRow fila;
        TextView txtFecha;
        TextView txtDistancia;
        TextView txtVelMax;
        TextView txtVelPromedio;
        TextView txtTiempo;

        SQLiteDatabase db = MainActivity.usdbh.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Stats", null);

        if (cursor.moveToLast())
            do {
                fila = new TableRow(super.getActivity());
                fila.setLayoutParams(layoutFila);

                txtFecha = new TextView(super.getActivity());
                txtDistancia = new TextView(super.getActivity());
                txtVelMax = new TextView(super.getActivity());
                txtVelPromedio = new TextView(super.getActivity());
                txtTiempo = new TextView(super.getActivity());

                txtFecha.setText(cursor.getString(0));
                txtFecha.setGravity(Gravity.CENTER_HORIZONTAL);
                txtFecha.setTextAppearance(super.getActivity(), R.style.etiqueta);
                txtFecha.setBackgroundResource(R.drawable.tabla_celda);
                txtFecha.setLayoutParams(layoutFecha);

                txtDistancia.setText(cursor.getString(1));
                txtDistancia.setGravity(Gravity.CENTER_HORIZONTAL);
                txtDistancia.setTextAppearance(super.getActivity(), R.style.etiqueta);
                txtDistancia.setBackgroundResource(R.drawable.tabla_celda);
                txtDistancia.setLayoutParams(layoutDistancia);

                txtVelMax.setText(cursor.getString(2));
                txtVelMax.setGravity(Gravity.CENTER_HORIZONTAL);
                txtVelMax.setTextAppearance(super.getActivity(), R.style.etiqueta);
                txtVelMax.setBackgroundResource(R.drawable.tabla_celda);
                txtVelMax.setLayoutParams(layoutVelMax);

                txtVelPromedio.setText(cursor.getString(3));
                txtVelPromedio.setGravity(Gravity.CENTER_HORIZONTAL);
                txtVelPromedio.setTextAppearance(super.getActivity(), R.style.etiqueta);
                txtVelPromedio.setBackgroundResource(R.drawable.tabla_celda);
                txtVelPromedio.setLayoutParams(layoutVelPromedio);

                txtTiempo.setText(cursor.getString(4));
                txtTiempo.setGravity(Gravity.CENTER_HORIZONTAL);
                txtTiempo.setTextAppearance(super.getActivity(), R.style.etiqueta);
                txtTiempo.setBackgroundResource(R.drawable.tabla_celda);
                txtTiempo.setLayoutParams(layoutTiempo);

                fila.addView(txtFecha);
                fila.addView(txtDistancia);
                fila.addView(txtVelMax);
                fila.addView(txtVelPromedio);
                fila.addView(txtTiempo);

                tabla.addView(fila);

            }while (cursor.moveToPrevious());

    }


}
