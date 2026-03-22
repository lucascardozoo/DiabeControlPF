package frgp.utn.edu.ar.appmobile;

import android.content.Intent;
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

        // Invocacion de funcion validaciones
        // Si devuelve false, el boton no redirecciona a la pantalla principal
        if (!validaciones())
        {
            return;
        }

        bd = new OpenHelper(this, "DiabeControDB", null, 1);
        usuario = new Usuario();
        usuario.setEmail(etEmail.getText().toString());
        usuario.setContrasenia(etContrasenia.getText().toString());
        if(!bd.buscarUsuario(usuario))
        {
            Toast.makeText(this, "Email y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            return;
        }

        etEmail.setText("");
        etContrasenia.setText("");
        intent = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(intent);
    }

    public void eventoBtnRegistrarse(View view) {
        intent = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(intent);
    }

}