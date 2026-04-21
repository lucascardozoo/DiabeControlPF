package frgp.utn.edu.ar.appmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import Entidad.Comidas;
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

        //Obteneos controles
        etCantCarbohidratos = view.findViewById(R.id.etCantCarbohidratos);
        txtInputEt = view.findViewById(R.id.txtInputEt);

        //Click del botón
        Button btnGuardar = view.findViewById(R.id.btnRegistrarComida);
        btnGuardar.setOnClickListener(this::eventoBtnGuardarComida);

        return view;
    }

    public boolean validacionesComida(){

        boolean estado = true;

        etCantCarbohidratos.setError(null);

        //Validación cantidad de carbohidratos
        if (etCantCarbohidratos.getText().toString().isEmpty())
        {
            etCantCarbohidratos.setError("Campo requerido");
            estado = false;
        }
        return estado;
    }

    public void eventoBtnGuardarComida(View view){

        if(!validacionesComida())
        {
            return;
        }

        long resultado;
        long idGlucemia;

        bd = new OpenHelper(requireContext(), "DiabeControlDB", null, 1);
        Comidas comida = new Comidas();

        //Obtener email desde SharedPreferences
        SharedPreferences prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
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

        prefs = requireActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        idGlucemia = prefs.getLong("id_glucemia", -1);
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

            //Evitar crash si la Activity no está bien referenciada
            if(getActivity() instanceof PrincipalActivity){
                ((PrincipalActivity)getActivity()).irATabAsistente();
            }
        }
    }
}