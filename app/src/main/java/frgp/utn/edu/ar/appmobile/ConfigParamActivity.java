package frgp.utn.edu.ar.appmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import Entidad.ParametrosConfig;
import Entidad.TipoInsulina;
import Entidad.Usuario;
import OpenHelper.OpenHelper;
import adapter.SpinnerTipoInsulinaAdapter;

public class ConfigParamActivity extends AppCompatActivity {

    Intent intent;
    private EditText etFactCorreccion;
    private EditText etUmbralMin;
    private EditText etUmbralMax;
    private EditText etRelacionInsuHidrato;
    private Spinner spinnerR;
    private Spinner spinnerB;
    private ArrayList<TipoInsulina> todas = new ArrayList<>();
    private ArrayList<TipoInsulina> listaR = new ArrayList<>();
    private ArrayList<TipoInsulina> listaB = new ArrayList<>();
    private ParametrosConfig parametrosConfig;
    private OpenHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_config_param);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etFactCorreccion = findViewById(R.id.etFactCorreccion);
        etUmbralMin = findViewById(R.id.etUmbralMin);
        etUmbralMax = findViewById(R.id.etUmbralMax);
        etRelacionInsuHidrato = findViewById(R.id.etRelacionInsuHidrato);

        spinnerR = (Spinner)findViewById(R.id.spRapida);
        spinnerB = (Spinner)findViewById(R.id.spBasal);

        todas.add(new TipoInsulina(1, "Aspartica", "Rapida"));
        todas.add(new TipoInsulina(2, "Lispro", "Rapida"));
        todas.add(new TipoInsulina(3, "Regular", "Rapida"));
        todas.add(new TipoInsulina(4, "Glargina", "Basal"));
        todas.add(new TipoInsulina(5, "Detemir", "Basal"));
        todas.add(new TipoInsulina(6, "Degludec", "Basal"));

        for(TipoInsulina i : todas){
            if(i.getTipoInsulina().equals("Rapida")){
                listaR.add(i);
            }else if(i.getTipoInsulina().equals("Basal")){
                listaB.add(i);
            }
        }
        SpinnerTipoInsulinaAdapter adapterR = new SpinnerTipoInsulinaAdapter(this,listaR);
        spinnerR.setAdapter(adapterR);
        SpinnerTipoInsulinaAdapter adapterB = new SpinnerTipoInsulinaAdapter(this,listaB);
        spinnerB.setAdapter(adapterB);
    }
    public boolean validacionesConfig() {
        boolean estado = true;

        etFactCorreccion.setError(null);
        etUmbralMin.setError(null);
        etUmbralMax.setError(null);
        etRelacionInsuHidrato.setError(null);

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

        if(!validacionesConfig())
        {
            return;
        }

        bd = new OpenHelper(this, "DiabeControDB", null, 1);
        parametrosConfig = new ParametrosConfig();
        parametrosConfig.setEmailUsuario(getIntent().getStringExtra("email"));
        parametrosConfig.setTipoInsulinaRapida(spinnerR.getSelectedItem().toString());
        parametrosConfig.setTipoInsulinaBasal(spinnerB.getSelectedItem().toString());
        parametrosConfig.setFactorCorreccion(etFactCorreccion.getText().toString());
        parametrosConfig.setUmbralMinCorreccion(etUmbralMin.getText().toString());
        parametrosConfig.setUmbralMaxCorreccion(etUmbralMax.getText().toString());
        parametrosConfig.setRelacionInsulinaHidratos(etRelacionInsuHidrato.getText().toString());

        long resultado = bd.insertarConfiguracion(parametrosConfig);

        if(resultado == -1)
        {
            Toast.makeText(this, "Error al guardar configuración", Toast.LENGTH_SHORT).show();
            return;
        }
        etFactCorreccion.setText("");
        etUmbralMin.setText("");
        etUmbralMax.setText("");
        etRelacionInsuHidrato.setText("");
        intent = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(intent);
    }
}