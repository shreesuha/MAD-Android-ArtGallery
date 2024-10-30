// LoginActivity.java
package com.example.artgallery.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.artgallery.R;
import com.example.artgallery.Activity.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        usernameEditText = findViewById(R.id.username); // Assuming this is your username EditText
        passwordEditText = findViewById(R.id.logpswd); // Assuming this is your password EditText
    }

    public void login(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            boolean isValidUser  = dbHelper.checkUser (username, password);
            if (isValidUser ) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                // Redirect to HomeActivity
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish(); // Optional: Close this activity
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

}