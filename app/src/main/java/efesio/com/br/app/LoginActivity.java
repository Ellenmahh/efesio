package efesio.com.br.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.LoginBusiness;
import efesio.com.br.app.entities.Login;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.RuntimeValues;

public class LoginActivity extends ActivityBase
        implements Request.OnResult<Login>, Request.OnError, Request.OnStart, Request.OnFinish {

    EditText login_user;
    EditText password_user;
    Spinner igrejaSpinner;
    String login;
    ArrayList<Integer> conta;
    ArrayAdapter igrejaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btn_login = findViewById(R.id.btn_login);
        login_user = findViewById(R.id.login_user);
        password_user = findViewById(R.id.password_user);
        igrejaSpinner = findViewById(R.id.igrejaSpinner);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
//        login_user.setText("ellen@prompweb.com.br");
//        password_user.setText("123");

        login = getIntent().getStringExtra("email");
        conta = getIntent().getIntegerArrayListExtra("idEmpresa");

        System.out.println("email login "+login);
        System.out.println("idEmpresa login "+conta);

        login_user.setText(login);
//        password_user.setText("123");
        igrejaAdapter = new ArrayAdapter<>(LoginActivity.this, R.layout.fragment_busca_igreja_lista_item, R.id.igreja_rv);
        if (conta != null){
            igrejaAdapter.add(conta);
        }
        igrejaSpinner.setAdapter(igrejaAdapter);


    }

    private void login() {
        if(login_user.getText().toString().isEmpty()){
            Snackbar.make(login_user, "Login inválido", Snackbar.LENGTH_LONG).show();
            return;
        }else if(password_user.getText().toString().isEmpty()){
            Snackbar.make(password_user, "Senha inválida", Snackbar.LENGTH_LONG).show();
            return;
        }
        new LoginBusiness(this)
                .login(login_user.getText().toString(),(password_user.getText().toString()),igrejaSpinner )
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();
    }

    @Override
    public void onStart(String tag) {
        loading(true);
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        alert("Ocorreu um erro que impediu seu login, tente novamente mais tarde."+e.getMessage());
    }

    @Override
    public void onResult(String tag, NixResponse<Login> res) {
        String token = res.getHeaders().get("x-token");
        RuntimeValues.setToken(token);
        if (res.getStatus() != 201){
            alert(res.getMessage());
        }else{
            open(MainActivity.class);
            finish();
        }
    }

    @Override
    public void onFinish(String tag) {
        loading(false);
    }
}

