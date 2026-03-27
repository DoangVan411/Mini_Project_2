package com.nhom2.mini_project_2.view;

import android.os.Bundle;
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
        AuthController authController = new AuthController(this);
        UserEntity user = authController.currentUser();
        if (user != null) {
            tvWelcome.setText("Da dang nhap voi tai khoan: " + user.username);
        } else {
            tvWelcome.setText("Chua co thong tin dang nhap");
        }
    }
}