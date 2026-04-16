package frgp.utn.edu.ar.appmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragmentAsistente extends Fragment {

    public fragmentAsistente() {}
    public static fragmentAsistente newInstance(String param1, String param2) {
        fragmentAsistente fragment = new fragmentAsistente();
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
        return inflater.inflate(R.layout.fragment_asistente, container, false);
    }

    public void eventoBtnCalcular(View view){
    }
}