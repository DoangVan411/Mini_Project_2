package com.nhom2.mini_project_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.controller.AuthController;
import com.nhom2.mini_project_2.model.entity.UserEntity;

public class LoginActivity extends AppCompatActivity {
    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authController = new AuthController(this);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Vui long nhap day du tai khoan va mat khau", Toast.LENGTH_SHORT).show();
                return;
            }

            UserEntity user = authController.login(username, password);
            if (user == null) {
                Toast.makeText(this, "Sai tai khoan hoac mat khau", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Dang nhap thanh cong: " + user.fullName, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
