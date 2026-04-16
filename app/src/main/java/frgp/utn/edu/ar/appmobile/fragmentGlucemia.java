package frgp.utn.edu.ar.appmobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Entidad.EstacionAlimenticia;
import Entidad.Glucemias;
import OpenHelper.OpenHelper;
import adapter.SpinnerEstacionAlimenticia;

public class fragmentGlucemia extends Fragment {
    private EditText etNivelGlucemia;
    private Spinner spinnerEstacionAlimenticia;
    private EditText etHorarioDeMedicion;
    private OpenHelper bd;
    private ArrayList<EstacionAlimenticia> lista = new ArrayList<>();

    public fragmentGlucemia() {}

    public static fragmentGlucemia newInstance(String param1, String param2) {
        fragmentGlucemia fragment = new fragmentGlucemia();
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
        // Creamos la vista
        View view = inflater.inflate(R.layout.fragment_glucemia, container, false);

        // Obtenemos controles
        etNivelGlucemia = view.findViewById(R.id.etNivelGlucemia);
        etHorarioDeMedicion = view.findViewById(R.id.etHorarioDeMedicion);

        spinnerEstacionAlimenticia = (Spinner)view.findViewById(R.id.spinnerEstacionAlimenticia);

        lista.add(new EstacionAlimenticia(0, "Seleccione una estación alimenticia"));
        lista.add(new EstacionAlimenticia(1, "Desayuno"));
        lista.add(new EstacionAlimenticia(2, "Almuerzo"));
        lista.add(new EstacionAlimenticia(3, "Merienda"));
        lista.add(new EstacionAlimenticia(4, "Cena"));
        lista.add(new EstacionAlimenticia(5, "Otros"));

        SpinnerEstacionAlimenticia adapter = new SpinnerEstacionAlimenticia(requireContext(), lista);
        spinnerEstacionAlimenticia.setAdapter(adapter);

        // Click del botón
        Button btnGuardar = view.findViewById(R.id.btnRegistrarGlucemia);
        btnGuardar.setOnClickListener(this::eventoBtnGuardarGlucemia);

        return view;
    }

    public boolean validaciones() {
        boolean estado = true;

        etNivelGlucemia.setError(null);
        etHorarioDeMedicion.setError(null);

        // Validacion nivel de glucemia
        if (etNivelGlucemia.getText().toString().isEmpty())
        {
            etNivelGlucemia.setError("Campo requerido");
            estado = false;
        }

        // Validacion spinner estacion alimenticia
        if (spinnerEstacionAlimenticia.getSelectedItemPosition() == 0) {
            estado = false;
            Toast.makeText(getContext(), "Seleccione una opción", Toast.LENGTH_SHORT).show();
        }

        // Validacion horario de medicion
        if (etHorarioDeMedicion.getText().toString().isEmpty())
        {
            etHorarioDeMedicion.setError("Campo requerido");
            estado = false;
        }

        return estado;
    }

    public void eventoBtnGuardarGlucemia(View view) {

        if(!validaciones()) {return;}

        String fechaActual;
        long resultado;

        bd = new OpenHelper(requireContext(), "DiabeControDB", null, 1);
        Glucemias glucemia = new Glucemias();

        // Email del usuario logueado
        glucemia.setEmailUsuario(requireActivity().getIntent().getStringExtra("email"));

        // Datos de los controles
        glucemia.setNivelGlucemia(etNivelGlucemia.getText().toString());
        glucemia.setEstacionAlimenticia(spinnerEstacionAlimenticia.getSelectedItem().toString());
        glucemia.setHorario(etHorarioDeMedicion.getText().toString());

        // Fecha automática
        fechaActual = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        glucemia.setFecha(fechaActual);

        resultado = bd.insertarGlucemia(glucemia);

        if(resultado == -1)
        {
            Toast.makeText(getContext(), "Error al registrar glucemia", Toast.LENGTH_SHORT).show();
        }
        else
        {
            etNivelGlucemia.setText("");
            etHorarioDeMedicion.setText("");
            Toast.makeText(getContext(), "Glucemia registrada correctamente", Toast.LENGTH_SHORT).show();
            ((PrincipalActivity)getActivity()).irATabComida();
        }
    }
}