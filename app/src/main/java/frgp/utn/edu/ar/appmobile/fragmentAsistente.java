package frgp.utn.edu.ar.appmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Entidad.DosisResultado;
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
    Intent intent;

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

        Button btnConfig = view.findViewById(R.id.btnConfigParam);
        btnConfig.setOnClickListener(v -> eventoRedireccionConfigParam(v));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if(email != null){

            registro = bd.obtenerRegistros(email);

            if(registro != null && registro.getFactorCorreccion() != null && registro.getRelacionInsulinaHidratos() != null && registro.getUmbralMin() != null && registro.getUmbralMax() != null){
                txtGlucemiaR.setText(registro.getGlucemia());
                txtComidaR.setText(registro.getCarbohidratos());
                txtEstAlimenticiaR.setText(registro.getEstAlimenticia());
                txtRelacionInsuHidrato.setText(registro.getRelacionInsulinaHidratos());
                txtFactorCorreccionR.setText(registro.getFactorCorreccion());

                DosisResultado dosis = calcularDosis(registro);

                bd.actualizarDosis( registro.getIdGlucemia(), String.valueOf(dosis.getDosisTotal()), String.valueOf(dosis.getDosisCarbo()), String.valueOf(dosis.getDosisCorreccion()));

                txtDosisCarboR.setText(String.format("%.2f", dosis.getDosisCarbo()));
                txtDosisCorreccionR.setText(String.format("%.2f", dosis.getDosisCorreccion()));
                txtDosisFinal.setText(String.format("%.2f", dosis.getDosisTotal()));

            }
        }

    }

    private DosisResultado calcularDosis(RegistrosParaAsistente registro) {

        DosisResultado resultado = new DosisResultado();

        try {

            double glucemia = Double.parseDouble(registro.getGlucemia());
            double carbohidratos = Double.parseDouble(registro.getCarbohidratos());

            double factorCorreccion = Double.parseDouble(registro.getFactorCorreccion());
            double relacion = Double.parseDouble(registro.getRelacionInsulinaHidratos());

            double umbralMin = Double.parseDouble(registro.getUmbralMin());
            double umbralMax = Double.parseDouble(registro.getUmbralMax());

            if(relacion <= 0) relacion = 1;
            if(factorCorreccion <= 0) factorCorreccion = 1;

            // DOSIS POR CARBO
            resultado.dosisCarbo = carbohidratos / relacion;

            // DOSIS CORRECCIÓN
            if(glucemia <= umbralMin){
                resultado.dosisCorreccion = 0;
            }
            else if(glucemia > umbralMin && glucemia <= umbralMax){
                resultado.dosisCorreccion = (glucemia - umbralMin) / factorCorreccion;
            }
            else if(glucemia > umbralMax){
                resultado.dosisCorreccion = (umbralMax - umbralMin) / factorCorreccion;
            }

            // TOTAL
            resultado.dosisTotal = resultado.dosisCarbo + resultado.dosisCorreccion;

        }
        catch (Exception e){
            Toast.makeText(getContext(),"Debe configurar los parámetros del tratamiento", Toast.LENGTH_LONG).show();

            resultado.dosisCarbo = 0;
            resultado.dosisCorreccion = 0;
            resultado.dosisTotal = 0;
        }

        return resultado;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bd != null){
            bd.close();
        }
    }

    public void eventoRedireccionConfigParam(View view) {
        Intent intent = new Intent(requireContext(), ConfigParamActivity.class);
        startActivity(intent);
    }
}