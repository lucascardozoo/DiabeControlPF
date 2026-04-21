package frgp.utn.edu.ar.appmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

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

        bd = new OpenHelper(getContext(), "DiabeControlDB", null, 1);

        // Obtenemos email
        SharedPreferences prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if(email != null)
        {
            items = bd.obtenerHistorial(email);
        }

        GridViewAdapter adapter = new GridViewAdapter(getContext(),items);
        gridView = view.findViewById(R.id.gvHistorial);
        gridView.setAdapter(adapter);
        return view;
    }
}