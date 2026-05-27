package frgp.utn.edu.ar.appmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Entidad.Usuario;
import OpenHelper.OpenHelper;

public class LoginActivity extends AppCompatActivity {
    Intent intent;

    private EditText etEmail;
    private EditText etContrasenia;
    private Usuario usuario;
    private OpenHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        boolean sesionIniciada = prefs.getBoolean("sesion_iniciada", false);

        if (sesionIniciada) {
            Intent intent = new Intent(this, PrincipalActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etEmail = findViewById(R.id.etEmail);
        etContrasenia = findViewById(R.id.etContrasenia);
    }

    public boolean validaciones()
    {
        boolean estado = true;

        etEmail.setError(null);
        etContrasenia.setError(null);

        String regexContrasenia = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+/=.-]).{8,}$";

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

    public void eventoBtnIniciarSesion(View view) {

        // Validaciones
        if (!validaciones())
        {
            return;
        }

        bd = new OpenHelper(this, "DiabeControlDB", null, 1);
        usuario = new Usuario();

        String email = etEmail.getText().toString();
        String contrasenia = etContrasenia.getText().toString();

        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);

        if(!bd.buscarUsuario(usuario))
        {
            Toast.makeText(this, "Email y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener nombre del usuario desde BD
        String nombre = bd.obtenerNombrePorEmail(email);
        // Guardar sesión
        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", email);
        editor.putString("nombre", nombre);
        editor.putBoolean("sesion_iniciada", true);
        editor.apply();

        //Limpiar campos DESPUÉS de guardar el valor
        etEmail.setText("");
        etContrasenia.setText("");

        intent = new Intent(getApplicationContext(), PrincipalActivity.class);
        // Borra Login
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
    public void eventoBtnRegistrarse(View view) {
        intent = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bd != null) {
            bd.close();
        }
    }
}