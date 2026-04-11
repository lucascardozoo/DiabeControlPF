package frgp.utn.edu.ar.appmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import Entidad.Historial;
import adapter.GridViewAdapter;

public class fragmentHistorial extends Fragment {
    private ArrayList<Historial> items = new ArrayList<>();
    private GridView gridView;
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
        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        items.add(new Historial("10/04/2026","12:00","Almuerzo",90,60,6,"Arroz con pollo"));

        GridViewAdapter adapter = new GridViewAdapter(getContext(),items);
        gridView = view.findViewById(R.id.gvHistorial);
        gridView.setAdapter(adapter);
        return view;
    }
}