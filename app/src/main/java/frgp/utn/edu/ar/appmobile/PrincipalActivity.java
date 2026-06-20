package frgp.utn.edu.ar.appmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class PrincipalActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNav;
    private fragmentGlucemia fragGlucemia = new fragmentGlucemia();
    private fragmentComida fragComida = new fragmentComida();
    private fragmentAsistente fragAsistente = new fragmentAsistente();
    private fragmentHistorial fragHistorial = new fragmentHistorial();
    private TipoPantalla pantallaActual = TipoPantalla.MAIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        // Ajuste de márgenes por notch / barra estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                android.view.ViewGroup.MarginLayoutParams params =
                        (android.view.ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
                params.topMargin = systemBars.top;
                toolbar.setLayoutParams(params);
            }
            return insets;
        });

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // BottomNav
        bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_glucemia) {
                navegarA(fragGlucemia, PrincipalActivity.TipoPantalla.MAIN, false);

            } else if (item.getItemId() == R.id.nav_comida) {
                navegarA(fragComida, PrincipalActivity.TipoPantalla.MAIN, false);

            } else if (item.getItemId() == R.id.nav_asistente) {
                navegarA(fragAsistente, PrincipalActivity.TipoPantalla.MAIN, false);

            } else if (item.getItemId() == R.id.nav_historial) {
                navegarA(fragHistorial, PrincipalActivity.TipoPantalla.MAIN, false);
            }
            return true;
        });
        boolean irAConfig = getIntent().getBooleanExtra("irAConfig", false);
        if (irAConfig) {
            navegarA(new fragmentConfigParam(),
                    TipoPantalla.FULLSCREEN,
                    false);
        } else {
            navegarA(fragGlucemia,
                    TipoPantalla.MAIN,
                    false);
        }

        // DRAWER (MENÚ LATERAL)
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_usuario) {
                navegarA(new fragmentUsuario(), TipoPantalla.FULLSCREEN, true);

            } else if (id == R.id.nav_reportes) {
                navegarA(new fragmentReportesEstadisticas(), TipoPantalla.FULLSCREEN, true);

            } else if (id == R.id.nav_config) {
                navegarA(new fragmentConfigParam(), TipoPantalla.FULLSCREEN, true);

            } else if (id == R.id.nav_logout) {
                getSharedPreferences("usuario", MODE_PRIVATE).edit().clear().apply();

                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
            drawerLayout.closeDrawers();
            return true;
        });
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {

            Fragment actual = getSupportFragmentManager()
                    .findFragmentById(R.id.contenedorFragments);

            if (actual instanceof fragmentGlucemia ||
                    actual instanceof fragmentComida ||
                    actual instanceof fragmentAsistente ||
                    actual instanceof fragmentHistorial) {

                bottomNav.setVisibility(View.VISIBLE);

            } else {
                bottomNav.setVisibility(View.GONE);
            }
        });
    }

    // METODO CENTRAL DE NAVEGACIÓN

    public void navegarA(Fragment fragment, TipoPantalla tipo, boolean agregarAlBackStack) {
        pantallaActual = tipo;

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.fade_out
                );

        transaction.replace(R.id.contenedorFragments, fragment);

        if (agregarAlBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
        // Mostrar / ocultar BottomNav
        bottomNav.setVisibility(tipo == TipoPantalla.MAIN ? View.VISIBLE : View.GONE);
    }

    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    public enum TipoPantalla {
        MAIN,
        FULLSCREEN
    }
}


