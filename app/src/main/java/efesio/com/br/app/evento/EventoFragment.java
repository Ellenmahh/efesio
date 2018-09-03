package efesio.com.br.app.evento;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import efesio.com.br.app.R;
import efesio.com.br.app.entities.Evento;
import efesio.com.br.app.rest.Service;

public class EventoFragment extends Fragment {

    private NetworkImageView img_evento;
    private TextView titulo_evento,descricao_evento, tipo_evento, data_ini, data_fim,hora_ini ,hora_fim;
    private Context context;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private Evento evento;

    public static EventoFragment getInstance(Evento evento){
        EventoFragment f = new EventoFragment();
        f.evento = evento;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.evento_details, container, false);
        this.context= getActivity();
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });

        descricao_evento = rootView.findViewById(R.id.descricao_evento);
        titulo_evento = rootView.findViewById(R.id.titulo_evento);
        img_evento = rootView.findViewById(R.id.img_evento);
        tipo_evento = rootView.findViewById(R.id.tipo_evento);
        data_ini = rootView.findViewById(R.id.data_ini);
        data_fim = rootView.findViewById(R.id.data_fim);
        hora_ini = rootView.findViewById(R.id.hora_ini);
        hora_fim = rootView.findViewById(R.id.hora_fim);

        img_evento.setImageUrl(Service.EFESIO.getStorage()+"efesio-bucket-evento-banner/"+evento.getUrlBanner(), mImageLoader);
        tipo_evento.setText(evento.getTipo());
        titulo_evento.setText(evento.getNome());
        descricao_evento.setText(evento.getDescricao());
        data_ini.setText(evento.getDataInicio().toString("dd/MM"));
        data_fim.setText(evento.getDataTermino().toString("dd/MM"));
        hora_ini.setText(evento.getHoraInicio());
        hora_fim.setText(evento.getHoraTermino());

        return rootView;
    }

//    public interface OnItemSelectedListener {
//        void onItemSelected(String link);
//    }
//    // Dispara o metodo implementado pela Activity
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnItemSelectedListener) {
//            listener = (AdapterView.OnItemSelectedListener) context;
//        } else {
//            throw new ClassCastException();
//        }
//    }

}
