package efesio.com.br.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import efesio.com.br.app.business.LoginBusiness;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_user)
    EditText login_user;
    @BindView(R.id.password_user)
    EditText password_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        login_user.setText("asd");
        password_user.setText("asd@041296");
    }

    private void login() {
        if(login_user.getText().toString().replaceAll("\\D", "").isEmpty()){
            Snackbar.make(login_user, "Login inválido", Snackbar.LENGTH_LONG).show();
            return;
        }else if(password_user.getText().toString().isEmpty()){
            Snackbar.make(password_user, "Senha inválida", Snackbar.LENGTH_LONG).show();
            return;
        }
        new LoginBusiness(this)
                .login(login_user.getText().toString().replaceAll("\\D", ""), Util.toMD5(txtSenha.getText().toString()))
                .setOnStart(this)
                .setOnError(this)
                .setOnResult(this)
                .setOnFinish(this)
                .fire();
    }
}

