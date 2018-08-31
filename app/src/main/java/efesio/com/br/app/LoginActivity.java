package efesio.com.br.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.LoginBusiness;
import efesio.com.br.app.entities.Login;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.RuntimeValues;
import efesio.com.br.app.util.Util;

public class LoginActivity extends ActivityBase
        implements Request.OnResult<Login>, Request.OnError, Request.OnStart, Request.OnFinish {

    private EditText login_user,password_user;
    String login;
    Button btn_login,btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btn_login = findViewById(R.id.btn_login);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        login_user = findViewById(R.id.login_user);
        password_user = findViewById(R.id.password_user);

        login = getIntent().getStringExtra("email");

        if (login != null){
            login_user.setText(login);
        }else{
            login_user.setText("estevao@email.com");
        }
            password_user.setText("123");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(CadastroActivity.class);
            }
        });
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
                .login(login_user.getText().toString(),(Util.toMD5(password_user.getText().toString())))
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
            RuntimeValues.setIdEmpresa(res.getEntity().getIdEmpresa());
            open(MainActivity.class);
            finish();
        }
    }

    @Override
    public void onFinish(String tag) {
        loading(false);
    }
}

