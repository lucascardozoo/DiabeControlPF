package frgp.utn.edu.ar.appmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import adapter.TabAdapterViewPager;

public class PrincipalActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                android.view.ViewGroup.MarginLayoutParams params =
                        (android.view.ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
                params.topMargin = systemBars.top;
                toolbar.setLayoutParams(params);

                toolbar.setPadding(0, 0, 0, 0);
            }

            TabLayout tabLayout = findViewById(R.id.tabLayout);
            if (tabLayout != null) {
                tabLayout.setPadding(0, 0, 0, systemBars.bottom);
            }
            return insets;
        });

        // 1. Configurar la Toolbar para mostrar las 3 rayitas
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. Configurar el DrawerLayout y el botón hamburguesa (Toggle)
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(android.graphics.Color.BLACK);
        }

        // 3. Controlar los clics en los elementos del menú lateral
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_usuario) {
                    Intent intent = new Intent(PrincipalActivity.this, UsuarioActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_config) {
                    Intent intent = new Intent(PrincipalActivity.this, ConfigParamActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_reportes) {
                    Intent intent = new Intent(PrincipalActivity.this, Reportes_EstadisticasActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_logout) {
                    // Borra los datos guardados del usuario
                    getSharedPreferences("usuario", MODE_PRIVATE).edit().clear().apply();
                    // Volver al Login
                    Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                // Cierra el menú deslizable tras la selección
                drawerLayout.closeDrawers();
                return true;
            }
        });

        // 4. Tu lógica original de pestañas (TabLayout y ViewPager2)
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.tabViewPager);

        TabAdapterViewPager adapterTab = new TabAdapterViewPager(this);
        viewPager2.setAdapter(adapterTab);

        final String[] titles = new String[]{"Glucemia", "Comida", "Asistente", "Historial"};

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(titles[position])
        ).attach();
    }

    public void irATabComida() {viewPager2.setCurrentItem(1);}
    public void irATabAsistente() {viewPager2.setCurrentItem(2);}
}
