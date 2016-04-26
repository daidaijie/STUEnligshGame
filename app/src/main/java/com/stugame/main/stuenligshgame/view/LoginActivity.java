package com.stugame.main.stuenligshgame.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.stugame.main.stuenligshgame.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.usernameEditText)
    EditText usernameEditText;

    @Bind(R.id.passwordEditText)
    EditText passwordEditText;

    @Bind(R.id.loginButton)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//      设置toolbar
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

    }
    @OnClick(R.id.loginButton)
    public void onClick() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
