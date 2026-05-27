package frgp.utn.edu.ar.appmobile;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragmentUsuario extends Fragment {
    private TextView txtNombreUsuario;
    private TextView txtEmailUsuario;

    public fragmentUsuario() {
        // Constructor vacío obligatorio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        // Referencias a los TextView (IMPORTANTE: usando view)
        txtNombreUsuario = view.findViewById(R.id.txtNombre);
        txtEmailUsuario = view.findViewById(R.id.txtEmail);

        // Obtener datos guardados
        SharedPreferences prefs = getActivity().getSharedPreferences("usuario", getActivity().MODE_PRIVATE);

        String nombre = prefs.getString("nombre","Sin nombre");
        String email = prefs.getString("email","Sin email");

        // Mostrar datos
        txtNombreUsuario.setText("Nombre: " + nombre);
        txtEmailUsuario.setText("Email: " + email);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = getActivity().getSharedPreferences("usuario", getActivity().MODE_PRIVATE);

        String nombre = prefs.getString("nombre","Sin nombre");
        String email = prefs.getString("email","Sin email");

        txtNombreUsuario.setText("Nombre: " + nombre);
        txtEmailUsuario.setText("Email: " + email);
    }
}