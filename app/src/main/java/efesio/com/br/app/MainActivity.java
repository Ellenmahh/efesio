package efesio.com.br.app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import efesio.com.br.app.agenda.AgendaActivity;
import efesio.com.br.app.aniversariante.AniversarianteActivity;
import efesio.com.br.app.evento.EventoActivity;
import efesio.com.br.app.galeria.GaleriaActivity;
import efesio.com.br.app.util.RuntimeValues;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imageView_nav;
    TextView nome_igreja,email_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout_nav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nome_igreja = headerView.findViewById(R.id.nome_igreja);
        email_user = headerView.findViewById(R.id.email_user);
        imageView_nav = headerView.findViewById(R.id.imageView_nav);

        System.out.println("foto igreja -- " + RuntimeValues.getFotoIgreja());
        System.out.println("nome igreja -- " + RuntimeValues.getNomeIgreja());

        nome_igreja.setText(RuntimeValues.getNomeIgreja());
        email_user.setText(RuntimeValues.getEmail());

        imageView_nav.setImageDrawable(Drawable.createFromPath(RuntimeValues.getFotoIgreja()));

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_nav);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, GaleriaActivity.class);
            startActivity(intent);
        } else if (id == R.id.agenda) {
            Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
            startActivity(intent);
        } else if (id == R.id.eventos) {
            Intent intent = new Intent(MainActivity.this, EventoActivity.class);
            startActivity(intent);

        }else if (id == R.id.logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish() ; // This call is missing.
        } else if (id == R.id.aniversariantes) {
            Intent intent = new Intent(MainActivity.this, AniversarianteActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_nav);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
