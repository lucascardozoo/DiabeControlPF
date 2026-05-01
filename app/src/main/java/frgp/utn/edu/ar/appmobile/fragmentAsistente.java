package frgp.utn.edu.ar.appmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Entidad.RegistrosParaAsistente;
import OpenHelper.OpenHelper;

public class fragmentAsistente extends Fragment {
    private OpenHelper bd;
    private TextView txtGlucemiaR;
    private TextView txtComidaR;
    private TextView txtEstAlimenticiaR;
    private TextView txtRelacionInsuHidrato;
    private TextView txtFactorCorreccionR;
    private TextView txtDosisCarboR;
    private TextView txtDosisCorreccionR;
    private TextView txtDosisFinal;
    RegistrosParaAsistente registro;

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

        View view = inflater.inflate(R.layout.fragment_asistente, container, false);

        bd = new OpenHelper(getContext(), "DiabeControlDB", null, 1);

        txtGlucemiaR = view.findViewById(R.id.txtGlucemiaR);
        txtComidaR = view.findViewById(R.id.txtComidaR);
        txtEstAlimenticiaR = view.findViewById(R.id.txtEstAlimenticiaR);
        txtRelacionInsuHidrato = view.findViewById(R.id.txtRelacionInsuHidrato);
        txtFactorCorreccionR = view.findViewById(R.id.txtFactorCorreccionR);
        txtDosisCarboR = view.findViewById(R.id.txtDosisCarboR);
        txtDosisCorreccionR = view.findViewById(R.id.txtDosisCorrecionR);
        txtDosisFinal = view.findViewById(R.id.txtDosisFinal);

        Button btnGuardar = view.findViewById(R.id.btnCalcular);
        btnGuardar.setOnClickListener(this::eventoBtnCalcular);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if(email != null){
            registro = bd.obtenerRegistros(email);

            if(registro != null){
                txtGlucemiaR.setText(registro.getGlucemia());
                txtComidaR.setText(registro.getCarbohidratos());
                txtEstAlimenticiaR.setText(registro.getEstAlimenticia());
                txtRelacionInsuHidrato.setText(registro.getRelacionInsulinaHidratos());
                txtFactorCorreccionR.setText(registro.getFactorCorreccion());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bd != null){
            bd.close();
        }
    }
    public void eventoBtnCalcular(View view){

    }
}