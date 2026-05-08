package frgp.utn.edu.ar.appmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Entidad.Historial;
import OpenHelper.OpenHelper;
import adapter.GridViewAdapter;

public class fragmentHistorial extends Fragment {
    private ArrayList<Historial> items = new ArrayList<>();
    private GridView gridView;

    private OpenHelper bd;

    public fragmentHistorial() {
    }
    public static fragmentHistorial newInstance(String param1, String param2) {
        fragmentHistorial fragment = new fragmentHistorial();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        gridView = view.findViewById(R.id.gvHistorial);

        bd = new OpenHelper(getContext(), "DiabeControlDB", null, 1);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(bd == null){
            bd = new OpenHelper(getContext(), "DiabeControlDB", null, 1);
        }

        SharedPreferences prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if(email != null)
        {
            items.clear();
            items.addAll(bd.obtenerHistorial(email));

            for (Historial h : items) {
                try {
                    SimpleDateFormat origen = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    SimpleDateFormat destino = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                    Date fecha = origen.parse(h.getFecha());

                    if(fecha != null){
                        h.setFecha(destino.format(fecha));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            GridViewAdapter adapter = new GridViewAdapter(getContext(), items);
            gridView.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bd != null){
            bd.close();
        }
    }
}