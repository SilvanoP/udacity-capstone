package com.udacity.animal.feature.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.udacity.animal.AnimalApplication;
import com.udacity.animal.R;
import com.udacity.animal.data.entities.local.User;
import com.udacity.animal.feature.shared.MainActivity;
import com.udacity.animal.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements AuthenticationContract.View {

    @BindView(R.id.login_username_edit)
    EditText usernameEdit;
    @BindView(R.id.login_email_edit)
    EditText emailEdit;
    @BindView(R.id.login_password_edit)
    EditText passwordEdit;

    @Inject
    AuthenticationContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        AnimalApplication.getKitsuComponent().inject(this);
        mPresenter.setView(this);
    }

    @OnClick(R.id.login_sign_in_button)
    public void onLogin() {
        String username = usernameEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.toast_login_missing_fields, Toast.LENGTH_LONG).show();
        } else {
            mPresenter.onLogin(username, email, password);
        }
    }

    @Override
    public void onAuthSuccess(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.USER_EXTRA, user);
        intent.putExtra(Constants.IS_ANIME_EXTRA, true);
        startActivity(intent);
        finish(); // Removes this activity from stack
    }

    @Override
    public void onAuthError() {
        Toast.makeText(this, R.string.login_denied, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unbind();
    }
}
