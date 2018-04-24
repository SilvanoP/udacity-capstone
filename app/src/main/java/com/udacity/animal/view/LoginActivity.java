package com.udacity.animal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.udacity.animal.R;
import com.udacity.animal.presenter.LoginPresenter;
import com.udacity.animal.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.Callback {

    @BindView(R.id.login_username_edit)
    EditText usernameEdit;
    @BindView(R.id.login_password_edit)
    EditText passwordEdit;
    @BindView(R.id.login_sign_in_button)
    Button loginButton;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = usernameEdit.getText().toString() + ":" +
                        passwordEdit.getText().toString();
                String tokenBase64 = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);

                loginPresenter.refreshToken(tokenBase64.trim()); // First authentication requires token
                loginPresenter.verifyCredentials();
            }
        });
    }

    @Override
    public void onReceiveResultCredentials(boolean result) {
        if (result)
            Toast.makeText(this, R.string.login_successful, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.login_denied, Toast.LENGTH_SHORT).show();
    }
}
