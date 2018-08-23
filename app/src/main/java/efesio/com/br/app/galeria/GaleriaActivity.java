package efesio.com.br.app.galeria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import efesio.com.br.app.R;
import efesio.com.br.app.business.GaleriaBusiness;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;

public class GaleriaActivity extends AppCompatActivity
        implements Request.OnResult<List<String>>, Request.OnError, Request.OnStart, Request.OnFinish{
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        toolbar =  findViewById(R.id.toolbarGa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(gridAdapter);

        galeria();

    }
    private void galeria() {
        new GaleriaBusiness(this)
                .getGaleria()
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();
    }


    @Override
    public void onStart(String tag) {
        Toast.makeText(this,"Carregando imagens", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String tag, Exception e) {

        Toast.makeText(this,"Erro ao carregar imagens", Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    @Override
    public void onResult(String tag, NixResponse<List<String>> res) {
        if (res.getStatus() != 200){
            Toast.makeText(this,res.getMessage(),Toast.LENGTH_SHORT).show();
        }
        gridAdapter = new GridViewAdapter(this, R.layout.galeria_item, res.getEntity());
        gridView.setAdapter(gridAdapter);
    }

    @Override
    public void onFinish(String tag) {
        Toast.makeText(this,"Imagens Carregadas", Toast.LENGTH_SHORT).show();
    }

    static class ViewHolder {
        TextView imageTitle;
        NetworkImageView image;
    }

//     //Prepare some dummy data for gridview
//    private ArrayList<ImageItem> getData() {
//        final ArrayList<ImageItem> imageItems = new ArrayList<>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
//        for (int i = 0; i < imgs.length(); i++) {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
//            imageItems.add(new ImageItem(bitmap, "Image#" + i));
//        }
//        return imageItems;
//    }

}
