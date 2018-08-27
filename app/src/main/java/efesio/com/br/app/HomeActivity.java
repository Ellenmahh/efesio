package efesio.com.br.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import efesio.com.br.app.business.CidadeBusiness;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;

public class HomeActivity extends AppCompatActivity {
    private static final String KEY_STATE = "uf";
    private static final String KEY_CITIES = "nome_cidade";
    Spinner stateSpinner;
    Spinner citiesSpinner;
    private ProgressDialog pDialog;
    private String cities_url = "http://efesioapi.azurewebsites.net/api/cidade/consultar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        stateSpinner = findViewById(R.id.stateSpinner);
        citiesSpinner = findViewById(R.id.citiesSpinner);
//        displayLoader();
        loadStateCityDetails();

        Button submitButton = findViewById(R.id.buttonSubmit);

        //Display state and city name when submit button is pressed
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estado state = (Estado) stateSpinner.getSelectedItem();
                String city = citiesSpinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "Selected State: " + state.getUf()
                        + " City: " + city, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void displayLoader() {
        pDialog = new ProgressDialog(HomeActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void loadStateCityDetails() {

        final ArrayAdapter citiesAdapter = new ArrayAdapter<>(HomeActivity.this, R.layout.city_list, R.id.citySpinnerText);
        citiesSpinner.setAdapter(citiesAdapter);

        final EstadoAdapter stateAdapter = new EstadoAdapter(HomeActivity.this, R.layout.estado_list, R.id.spinnerText);
        stateSpinner.setAdapter(stateAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new CidadeBusiness(HomeActivity.this)
                        .get(stateAdapter.getItem(position).getUf())
                        .setOnStart(new Request.OnStart() {
                            @Override
                            public void onStart(String tag) {
                                Toast.makeText(HomeActivity.this, "Selecionou ", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setOnResult(new Request.OnResult<List<String>>() {
                    @Override
                    public void onResult(String tag, NixResponse<List<String>> res) {
                        citiesAdapter.clear();
                        if(res.getStatus() == 200){
                            citiesAdapter.addAll(res.getEntity());
                        }else{
                            citiesAdapter.add("Erro");
                        }
                    }
                }).fire();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                citiesAdapter.clear();
            }
        });
    }
}

