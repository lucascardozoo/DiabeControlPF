package frgp.utn.edu.ar.appmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Entidad.ParametrosConfig;
import Entidad.TipoInsulina;
import OpenHelper.OpenHelper;
import adapter.SpinnerTipoInsulinaAdapter;

public class fragmentConfigParam extends Fragment {
    private EditText etFactCorreccion, etUmbralMin, etUmbralMax, etRelacionInsuHidrato;
    private Spinner spinnerR, spinnerB;
    private ArrayList<TipoInsulina> todas = new ArrayList<>();
    private ArrayList<TipoInsulina> listaR = new ArrayList<>();
    private ArrayList<TipoInsulina> listaB = new ArrayList<>();
    private ParametrosConfig parametrosConfig;
    private OpenHelper bd;

    public fragmentConfigParam() {
    }

    public static fragmentConfigParam newInstance(String param1, String param2) {
        fragmentConfigParam fragment = new fragmentConfigParam();
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

        View view = inflater.inflate(R.layout.fragment_config_param, container, false);

        etFactCorreccion = view.findViewById(R.id.etFactCorreccion);
        etUmbralMin = view.findViewById(R.id.etUmbralMin);
        etUmbralMax = view.findViewById(R.id.etUmbralMax);
        etRelacionInsuHidrato = view.findViewById(R.id.etRelacionInsuHidrato);

        spinnerR = view.findViewById(R.id.spRapida);
        spinnerB = view.findViewById(R.id.spBasal);

        cargarSpinners();

        return view;
    }
    private void cargarSpinners() {

        todas.add(new TipoInsulina(0, "Seleccione una opción", "Rapida"));
        todas.add(new TipoInsulina(1, "Aspartica", "Rapida"));
        todas.add(new TipoInsulina(2, "Lispro", "Rapida"));
        todas.add(new TipoInsulina(3, "Regular", "Rapida"));

        todas.add(new TipoInsulina(0, "Seleccione una opción", "Basal"));
        todas.add(new TipoInsulina(1, "Glargina", "Basal"));
        todas.add(new TipoInsulina(2, "Detemir", "Basal"));
        todas.add(new TipoInsulina(3, "Degludec", "Basal"));

        for (TipoInsulina i : todas) {
            if (i.getTipoInsulina().equals("Rapida")) {
                listaR.add(i);
            } else {
                listaB.add(i);
            }
        }

        SpinnerTipoInsulinaAdapter adapterR =
                new SpinnerTipoInsulinaAdapter(getContext(), listaR);

        SpinnerTipoInsulinaAdapter adapterB =
                new SpinnerTipoInsulinaAdapter(getContext(), listaB);

        spinnerR.setAdapter(adapterR);
        spinnerB.setAdapter(adapterB);
    }
    public boolean validacionesConfig() {
        boolean estado = true;

        etFactCorreccion.setError(null);
        etUmbralMin.setError(null);
        etUmbralMax.setError(null);
        etRelacionInsuHidrato.setError(null);

        // Validacion spinner
        if (spinnerR.getSelectedItemPosition() == 0) {
            estado = false;
            Toast.makeText(getContext(), "Seleccione un tipo de insulina rapida", Toast.LENGTH_SHORT).show();
        }
        if (spinnerB.getSelectedItemPosition() == 0) {
            estado = false;
            Toast.makeText(getContext(), "Seleccione un tipo de insulina basal", Toast.LENGTH_SHORT).show();
        }

        // Validacion del factor de corrección
        if (etFactCorreccion.getText().toString().isEmpty())
        {
            etFactCorreccion.setError("Campo requerido");
            estado = false;
        }
        else
        {
            if (etFactCorreccion.getText().toString().length() > 2)
            {
                etFactCorreccion.setError("Máximo 2 caracteres");
                estado = false;
            }
        }

        // Validacion del umbral mínimo
        if (etUmbralMin.getText().toString().isEmpty())
        {
            etUmbralMin.setError("Campo requerido");
            estado = false;
        }
        else
        {
            if (etUmbralMin.getText().toString().length() > 3)
            {
                etUmbralMin.setError("Máximo 3 caracteres");
                estado = false;
            }
        }

        // Validacion del umbral máximo
        if (etUmbralMax.getText().toString().isEmpty())
        {
            etUmbralMax.setError("Campo requerido");
            estado = false;
        }
        else
        {
            if (etUmbralMax.getText().toString().length() > 3)
            {
                etUmbralMax.setError("Máximo 3 caracteres");
                estado = false;
            }
        }

        // Validacion de la relación insulina hidratos
        if (etRelacionInsuHidrato.getText().toString().isEmpty())
        {
            etRelacionInsuHidrato.setError("Campo requerido");
            estado = false;
        }
        else
        {
            if (etRelacionInsuHidrato.getText().toString().length() > 2)
            {
                etRelacionInsuHidrato.setError("Máximo 2 caracteres");
                estado = false;
            }
        }
        return estado;
    }
    public void eventoBtnGuardarConfiguracion(View view) {

        if (!validacionesConfig()) return;

        bd = new OpenHelper(getContext(), "DiabeControlDB", null, 1);
        parametrosConfig = new ParametrosConfig();

        SharedPreferences prefs = getContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if (email == null) {
            Toast.makeText(getContext(), "Usuario no identificado", Toast.LENGTH_SHORT).show();
            return;
        }

        parametrosConfig.setEmailUsuario(email);
        parametrosConfig.setTipoInsulinaRapida(spinnerR.getSelectedItem().toString());
        parametrosConfig.setTipoInsulinaBasal(spinnerB.getSelectedItem().toString());
        parametrosConfig.setFactorCorreccion(etFactCorreccion.getText().toString());
        parametrosConfig.setUmbralMinCorreccion(etUmbralMin.getText().toString());
        parametrosConfig.setUmbralMaxCorreccion(etUmbralMax.getText().toString());
        parametrosConfig.setRelacionInsulinaHidratos(etRelacionInsuHidrato.getText().toString());

        long resultado = bd.guardarConfiguracion(parametrosConfig);

        if (resultado == -1) {
            Toast.makeText(getContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getContext(), "Guardado correctamente", Toast.LENGTH_SHORT).show();

        if (getActivity() instanceof PrincipalActivity) {
            ((PrincipalActivity) requireActivity())
                    .navegarA(new fragmentGlucemia(), PrincipalActivity.TipoPantalla.MAIN, false);
        }
    }
    public void eventoBtnOmitir(View view) {
        if (getActivity() instanceof PrincipalActivity) {
            ((PrincipalActivity) requireActivity())
                    .navegarA(new fragmentGlucemia(), PrincipalActivity.TipoPantalla.MAIN, false);
        }
    }
}