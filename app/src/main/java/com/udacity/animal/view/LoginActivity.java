package com.udacity.animal.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.udacity.animal.R;
import com.udacity.animal.presenter.LoginPresenter;
import com.udacity.animal.presenter.LoginPresenterImpl;
import com.udacity.animal.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.Callback {

    private static final String LOGGED_USER_TOKEN = "LOGGED_USER_TOKEN";
    private static final String LOGGED_USER_NAME = "LOGGED_USER_NAME";


    @BindView(R.id.login_username_edit)
    EditText usernameEdit;
    @BindView(R.id.login_password_edit)
    EditText passwordEdit;
    @BindView(R.id.login_sign_in_button)
    Button loginButton;

    private LoginPresenter mLoginPresenter;
    private SharedPreferences mPref;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenter = new LoginPresenterImpl(this);

        // This is to avoid the user having to login every time
        mPref = getSharedPreferences(Constants.ANIMAL_SHARED_PREFS, Context.MODE_PRIVATE);
        final String userToken = mPref.getString(LOGGED_USER_TOKEN, "");

        if (!TextUtils.isEmpty(userToken)) {
            mUsername = mPref.getString(LOGGED_USER_NAME,"");
            mLoginPresenter.refreshToken(userToken);
            mLoginPresenter.verifyCredentials();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEdit.getText() == null || usernameEdit.getText().toString().trim().equals("")
                        || passwordEdit.getText() == null || passwordEdit.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.toast_login_missing_fields, Toast.LENGTH_LONG).show();
                } else {
                    mUsername = usernameEdit.getText().toString();
                    String token = mUsername + ":" + passwordEdit.getText().toString();
                    String tokenBase64 = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);

                    mPref.edit().putString(LOGGED_USER_TOKEN, tokenBase64.trim()).apply();
                    mPref.edit().putString(LOGGED_USER_NAME, mUsername).apply();
                    mLoginPresenter.refreshToken(tokenBase64.trim()); // First authentication requires token
                    mLoginPresenter.verifyCredentials();
                }
            }
        });
    }

    @Override
    public void onReceiveResultCredentials(boolean result) {
        if (result) {
            if (TextUtils.isEmpty(mUsername)) {
                return;
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constants.INTENT_EXTRA_USERNAME, mUsername);
            startActivity(intent);
        } else
            Toast.makeText(this, R.string.login_denied, Toast.LENGTH_SHORT).show();
    }
}
