package frgp.utn.edu.ar.appmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragmentComida extends Fragment {

    public fragmentComida() {}
    public static fragmentComida newInstance(String param1, String param2) {
        fragmentComida fragment = new fragmentComida();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comida, container, false);
    }
    public void eventoBtnGuardarComida(View view){
        ((PrincipalActivity)getActivity()).irATabAsistente();
    }
}