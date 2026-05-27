package frgp.utn.edu.ar.appmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ObjectInputStream;

import Entidad.Comidas;
import Entidad.DosisResultado;
import Entidad.RegistrosParaAsistente;
import OpenHelper.OpenHelper;

public class fragmentComida extends Fragment {

    private TextInputEditText txtInputEt;
    private EditText etCantCarbohidratos;
    private OpenHelper bd;

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

        //Creación de vista
        View view = inflater.inflate(R.layout.fragment_comida, container, false);

        //Obtenemos controles
        etCantCarbohidratos = view.findViewById(R.id.etCantCarbohidratos);
        txtInputEt = view.findViewById(R.id.txtInputEt);

        //Click botón guardar
        Button btnGuardar = view.findViewById(R.id.btnRegistrarComida);
        btnGuardar.setOnClickListener(this::eventoBtnGuardarComida);

        //Click botón omitir
        Button btnOmitir = view.findViewById(R.id.btnOmitirComida);
        btnOmitir.setOnClickListener(v -> omitirComida());

        return view;
    }

    public boolean validacionesComida(){

        double carbs;
        etCantCarbohidratos.setError(null);

        if (etCantCarbohidratos.getText().toString().isEmpty())
        {
            etCantCarbohidratos.setError("Campo requerido");
            return false;
        }

        try {
            carbs = Double.parseDouble(etCantCarbohidratos.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getContext(), "Carbohidratos inválidos", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void eventoBtnGuardarComida(View view){

        long idGlucemia;
        SharedPreferences prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        idGlucemia = prefs.getLong("id_glucemia", -1);
        if(idGlucemia == -1){
            Toast.makeText(getContext(), "No hay glucemia asociada. Registre una glucemia primero.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!validacionesComida())
        {
            return;
        }

        long resultado;
        double carbs;

        bd = new OpenHelper(requireContext(), "DiabeControlDB", null, 1);
        Comidas comida = new Comidas();

        //Obtener email desde SharedPreferences
        prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);

        //Validación importante
        if(email == null)
        {
            Toast.makeText(getContext(), "Error: usuario no identificado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Datos
        comida.setEmailUsuario(email);
        comida.setDescripcion(txtInputEt.getText().toString());
        comida.setCantCarbohidratos(etCantCarbohidratos.getText().toString());

        comida.setIdGlucemia(idGlucemia);

        resultado = bd.insertarComida(comida);

        if(resultado == -1){
            Toast.makeText(getContext(), "Error al registrar comida", Toast.LENGTH_SHORT).show();
        }
        else{
            //Limpiar controles
            txtInputEt.setText("");
            etCantCarbohidratos.setText("");

            Toast.makeText(getContext(), "Comida registrada correctamente", Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = prefs.edit();
            editor.remove("id_glucemia");
            editor.apply();

            if (getActivity() instanceof PrincipalActivity) {
                ((PrincipalActivity) requireActivity())
                        .navegarA(new fragmentAsistente(), PrincipalActivity.TipoPantalla.MAIN, false);
            }
        }
    }

    private void omitirComida() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        long idGlucemia = prefs.getLong("id_glucemia", -1);

        if(idGlucemia == -1){
            Toast.makeText(getContext(),
                    "Primero registre una glucemia",
                    Toast.LENGTH_SHORT).show();
            if (getActivity() instanceof PrincipalActivity) {
                ((PrincipalActivity) requireActivity())
                        .navegarA(new fragmentGlucemia(), PrincipalActivity.TipoPantalla.MAIN, false);
            }
            return;
        }

        bd = new OpenHelper(requireContext(), "DiabeControlDB", null, 1);

        Comidas comida = new Comidas();

        String email = prefs.getString("email", null);

        comida.setEmailUsuario(email);
        comida.setDescripcion("");
        comida.setCantCarbohidratos("0");
        comida.setIdGlucemia(idGlucemia);

        long resultado = bd.insertarComida(comida);

        if(resultado != -1){

            Toast.makeText(getContext(),"Comida omitida correctamente",Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = prefs.edit();
            editor.remove("id_glucemia");
            editor.apply();

            if (getActivity() instanceof PrincipalActivity) {
                ((PrincipalActivity) requireActivity())
                        .navegarA(new fragmentAsistente(), PrincipalActivity.TipoPantalla.MAIN, false);
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
}