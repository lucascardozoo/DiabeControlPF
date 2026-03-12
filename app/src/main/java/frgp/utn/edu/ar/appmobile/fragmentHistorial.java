package frgp.utn.edu.ar.appmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragmentHistorial extends Fragment {

    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    //private String mParam1;
    //private String mParam2;

    public fragmentHistorial() {
        // Required empty public constructor
    }
     //@param param1 Parameter 1.
     //@param param2 Parameter 2.
     //@return A new instance of fragment FragmentHistorial.
    public static fragmentHistorial newInstance(String param1, String param2) {
        fragmentHistorial fragment = new fragmentHistorial();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historial, container, false);
    }
}