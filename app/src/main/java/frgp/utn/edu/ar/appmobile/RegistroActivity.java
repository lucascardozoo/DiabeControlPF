package frgp.utn.edu.ar.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Entidad.Usuario;
import OpenHelper.OpenHelper;

public class RegistroActivity extends AppCompatActivity {

    Intent intent;
    private EditText etNombre;
    private EditText etEmail;
    private EditText etContrasenia;
    private Usuario usuario;
    private OpenHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etContrasenia = findViewById(R.id.etContrasenia);
    }

    public boolean validacionesRegistro()
    {
        boolean estado = true;

        etNombre.setError(null);
        etEmail.setError(null);
        etContrasenia.setError(null);

        String regexContrasenia = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+/=.-]).{8,}$";

        // Validacion de nombre
        if (etNombre.getText().toString().isEmpty())
        {
            etNombre.setError("Campo requerido");
            estado = false;
        }
        else
        {
            if (etNombre.getText().toString().length() < 3)
            {
                etNombre.setError("Mínimo 3 caracteres");
                estado = false;
            }
        }

        // Validacion de email
        if (etEmail.getText().toString().isEmpty())
        {
            etEmail.setError("Campo requerido");
            estado = false;
        }
        else
        {
            if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches())
            {
                etEmail.setError("Email inválido");
                estado = false;
            }
        }

        // Validacion de contraseña
        if (etContrasenia.getText().toString().isEmpty())
        {
            etContrasenia.setError("Campo requerido");
            estado = false;
        }
        else
        {
            if(!etContrasenia.getText().toString().matches(regexContrasenia))
            {
                etContrasenia.setError("Contraseña inválida");
                estado = false;
            }
        }

        return estado;
    }
    public void eventoBtnNuevoRegistro(View view) {
        long resultado;

        if(!validacionesRegistro())
        {
            return;
        }

        bd = new OpenHelper(this, "DiabeControDB", null, 1);
        usuario = new Usuario();
        usuario.setNombre(etNombre.getText().toString());
        usuario.setEmail(etEmail.getText().toString());
        usuario.setContrasenia(etContrasenia.getText().toString());
        resultado = bd.insertarUsuario(usuario);
        if(resultado == -2)
        {
            Toast.makeText(this, "Email ya registrado", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            if(resultado == -1)
            {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        etNombre.setText("");
        etEmail.setText("");
        etContrasenia.setText("");
        intent = new Intent(getApplicationContext(), ConfigParamActivity.class);
        startActivity(intent);
    }
}