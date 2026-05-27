package frgp.utn.edu.ar.appmobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UsuarioActivity extends AppCompatActivity {

    private TextView txtNombreUsuario;
    private TextView txtEmailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuario);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombreUsuario = findViewById(R.id.txtNombre);
        txtEmailUsuario = findViewById(R.id.txtEmail);

        // Obtener datos guardados
        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        String nombre = prefs.getString("nombre","Sin nombre");
        String email = prefs.getString("email","Sin email");

        // Mostrar datos
        txtNombreUsuario.setText("Nombre: " + nombre);
        txtEmailUsuario.setText("Email: " + email);
    }
}