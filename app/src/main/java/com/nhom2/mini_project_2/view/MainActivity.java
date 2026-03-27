package com.nhom2.mini_project_2.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom2.mini_project_2.R;
import com.nhom2.mini_project_2.controller.AuthController;
import com.nhom2.mini_project_2.model.entity.UserEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        Button btnGoLogin = findViewById(R.id.btnGoLogin);

        btnGoLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class))
        );
    }
}