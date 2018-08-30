package efesio.com.br.app.galeria;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.business.GaleriaBusiness;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.RuntimeValues;

public class GaleriaActivity extends AppCompatActivity
        implements Request.OnResult<List<String>>, Request.OnError, Request.OnStart, Request.OnFinish{
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    List<String> imageItems;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        toolbar =  findViewById(R.id.toolbarGa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getWindow().getAttributes().windowAnimations = R.style.AppTheme_Gal;

        imageItems = new ArrayList<>();
        gridView = findViewById(R.id.gridView);

        galeria();

    }

    private void showEditDialog(String s) {
        FragmentManager fm = getSupportFragmentManager();
        GaleriaDialogFragment editNameDialogFragment = new GaleriaDialogFragment();
        editNameDialogFragment.show(fm, s);
    }


    private void galeria(){
        new GaleriaBusiness(this)
                .galeria(RuntimeValues.getIdEmpresa())
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();

    }

    @Override
    public void onStart(String tag) {
        Toast.makeText(this,"Carregando imagens",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(String tag, NixResponse<List<String>> res) {

        if (res.getStatus() != 200){
            Toast.makeText(this,res.getMessage(),Toast.LENGTH_SHORT).show();
        }

//       gridAdapter.setItems(res.getEntity());
        gridAdapter = new GridViewAdapter(this,R.layout.galeria_item, (ArrayList<String>) res.getEntity());
        gridView.setAdapter(gridAdapter);
        imageItems.addAll(res.getEntity());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String x = String.valueOf(getIntent().putExtra("image", imageItems.get(position)));
                showEditDialog(x);
            }
        });
    }


    @Override
    public void onFinish(String tag) {


    }
}
