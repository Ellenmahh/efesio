package efesio.com.br.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import efesio.com.br.app.business.LoginBusiness;
import efesio.com.br.app.rest.NixResponse;
import efesio.com.br.app.rest.Request;
import efesio.com.br.app.util.Util;

public class LoginActivity extends AppCompatActivity implements Request.OnResult<Login>, Request.OnError, Request.OnStart, Request.OnFinish {

    EditText login_user;
    EditText password_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btn_login = findViewById(R.id.btn_login);
        login_user = findViewById(R.id.login_user);
        password_user = findViewById(R.id.password_user);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        login_user.setText("asd");
        password_user.setText("asd");
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
                .login(login_user.getText().toString().replaceAll("\\D", ""),
                        Util.toMD5(password_user.getText().toString()))
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();
    }

    @Override
    public void onResult(String tag, NixResponse<Login> res) {
        Toast.makeText(this, res.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String tag, Exception e) {
        e.printStackTrace();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(String tag) {
        Toast.makeText(this, "Fazendo o login", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinish(String tag) {
//        Toast.makeText(this, "Terminao", Toast.LENGTH_LONG).show();
    }
}

