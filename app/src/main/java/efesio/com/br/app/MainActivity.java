package efesio.com.br.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import efesio.com.br.app.agenda.AgendaActivity;
import efesio.com.br.app.aniversariante.AniversarianteActivity;
import efesio.com.br.app.evento.EventoActivity;
import efesio.com.br.app.feed.FeedFragment;
import efesio.com.br.app.galeria.GaleriaActivity;
import efesio.com.br.app.rest.Service;
import efesio.com.br.app.util.RuntimeValues;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private CircularNetworkImageView imageView_nav;
    private TextView nome_igreja, nome_user;
    private NetworkImageView logo;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Toolbar toolbar;
    private  String imgIgreja;
    private  String imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        imgIgreja = Service.EFESIO.getStorage()+"efesio-bucket-logo/"+RuntimeValues.getFotoIgreja();
        imgUser = Service.EFESIO.getStorage()+"efesio-bucket-membro/"+RuntimeValues.getImagem();
        toolbar =  findViewById(R.id.toolbar);
        if (RuntimeValues.getNomeIgreja() != null){
            toolbar.setTitle(RuntimeValues.getNomeIgreja());
        }
        setSupportActionBar(toolbar);
        DrawerLayout drawer =  findViewById(R.id.drawer_layout_nav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        /**
         * inicia a fila de requisições da voley*/
        mRequestQueue = Volley.newRequestQueue(this);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });

        logo = findViewById(R.id.logo);

        NavigationView navigationView =  findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        nome_igreja = headerView.findViewById(R.id.nome_igreja);
        imageView_nav = headerView.findViewById(R.id.imageView_nav);
        nome_user = headerView.findViewById(R.id.nome_user);

        if (RuntimeValues.getFotoIgreja() == null || RuntimeValues.getFotoIgreja().isEmpty()){
            logo.setImageResource(R.drawable.favicon);
        }
        logo.setImageUrl(imgIgreja,mImageLoader);

        nome_igreja.setText(RuntimeValues.getNomeIgreja());
        nome_user.setText("Bem vindo(a), "+RuntimeValues.getNomeUser());
        if (RuntimeValues.getImagem() == null || RuntimeValues.getImagem().isEmpty()){
            imageView_nav.setImageResource(R.drawable.noimage);
        }
        imageView_nav.setImageUrl(imgUser, mImageLoader);

        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.feed, FeedFragment.getInstance());
        ft.commit();
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
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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
            SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            editor.remove("email");
            editor.remove("senha");
            editor.apply();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.aniversariantes) {
            Intent intent = new Intent(MainActivity.this, AniversarianteActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_nav);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
