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
    public static final String EXTRA_REDIRECT_BOOKING = "extra_redirect_booking";
    public static final String EXTRA_SHOWTIME_ID = "extra_showtime_id";

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
                Toast.makeText(this, getString(R.string.login_missing_input), Toast.LENGTH_SHORT).show();
                return;
            }

            UserEntity user = authController.login(username, password);
            if (user == null) {
                Toast.makeText(this, getString(R.string.login_invalid_credentials), Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, getString(R.string.login_success, user.fullName), Toast.LENGTH_SHORT).show();
            boolean redirectBooking = getIntent().getBooleanExtra(EXTRA_REDIRECT_BOOKING, false);
            if (redirectBooking) {
                long showtimeId = getIntent().getLongExtra(EXTRA_SHOWTIME_ID, -1L);
                Intent bookingIntent = new Intent(this, BookTicketActivity.class);
                if (showtimeId > 0) {
                    bookingIntent.putExtra(BookTicketActivity.EXTRA_SHOWTIME_ID, showtimeId);
                }
                startActivity(bookingIntent);
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        });
    }
}
