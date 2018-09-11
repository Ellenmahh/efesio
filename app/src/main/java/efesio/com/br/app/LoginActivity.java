package efesio.com.br.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import efesio.com.br.app.base.ActivityBase;
import efesio.com.br.app.business.LoginBusiness;
import efesio.com.br.app.cadastro.CadastroActivity;
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
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getApplicationContext().getSharedPreferences("login",Context.MODE_PRIVATE);
        if ((pref.getString("email",null) != null) && (pref.getString("senha",null) != null)){
            login(pref.getString("email",null), pref.getString("senha",null));
        }else {
            setContentView(R.layout.login);

            btn_login = findViewById(R.id.btn_login);
            btn_cadastrar = findViewById(R.id.btn_cadastrar);
            login_user = findViewById(R.id.login_user);
            password_user = findViewById(R.id.password_user);

            login = getIntent().getStringExtra("email");

            if (login != null) {
                login_user.setText(login);
            }
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (login_user.getText().toString().isEmpty()) {
                        Snackbar.make(login_user, "Login inválido", Snackbar.LENGTH_LONG).show();
                        return;
                    } else if (password_user.getText().toString().isEmpty()) {
                        Snackbar.make(password_user, "Senha inválida", Snackbar.LENGTH_LONG).show();
                        return;
                    }

                    login(login_user.getText().toString(), Util.toMD5(password_user.getText().toString()));
                }
            });


            btn_cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    open(CadastroActivity.class);
                }
            });
        }
    }

    private void login(String email, String password) {
        RuntimeValues.setToken("null");

        new LoginBusiness(this)
                .login(email, password)
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
        alert("Ocorreu um erro que impediu seu login, tente novamente mais tarde.");
        editor = pref.edit();
        editor.remove("email");  // Saving string
        editor.remove("senha");  // Saving string
        editor.apply(); // com

    }

    @Override
    public void onResult(String tag, NixResponse<Login> res) {
        String token = res.getHeaders().get("x-token");
        RuntimeValues.setToken(token);
        if (res.getStatus() != 201){
            alert(res.getMessage());
            editor = pref.edit();
            editor.remove("email");  // Saving string
            editor.remove("senha");  // Saving string
            editor.apply(); // com
        }else{
            if ((pref.getString("email",null) == null) && (pref.getString("senha",null) == null)) {
                editor = pref.edit();
                editor.putString("email", login_user.getText().toString());  // Saving string
                editor.putString("senha", Util.toMD5(password_user.getText().toString()));  // Saving string
                editor.apply(); // commit changes
            }
            RuntimeValues.setIdAssinante(res.getEntity().getIdAssinante());
            RuntimeValues.setIdEmpresa(res.getEntity().getId());
            RuntimeValues.setEmail(res.getEntity().getEmail());
            RuntimeValues.setFotoIgreja(res.getEntity().getFotoIgreja());
            RuntimeValues.setNomeIgreja(res.getEntity().getNomeIgreja());
            RuntimeValues.setNomeUser(res.getEntity().getNomeUser());
            RuntimeValues.setImagem(res.getEntity().getFotoUser());
            open(MainActivity.class);
            finish();
        }
    }

    @Override
    public void onFinish(String tag) {
        loading(false);
    }
}

