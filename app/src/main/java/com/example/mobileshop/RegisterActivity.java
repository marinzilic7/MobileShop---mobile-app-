package com.example.mobileshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);

        TextView loginBtn = findViewById(R.id.loginBtn);
        Button registerButton = findViewById(R.id.registerButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void registerButton(View view){

        EditText firstName = findViewById(R.id.first_name);
        EditText lastName = findViewById(R.id.last_name);
        EditText email = findViewById(R.id.emailUser);
        EditText password = findViewById(R.id.passwordUser);

        String name = firstName.getText().toString();
        String surname = lastName.getText().toString();
        String emailUser = email.getText().toString();
        String passwordUser = email.getText().toString();

        if(name.isEmpty()) {
            Toast.makeText(this, "Unesite vase ime", Toast.LENGTH_SHORT).show();
        }else if(surname.isEmpty()){
            Toast.makeText(this, "Unesite vase prezime", Toast.LENGTH_SHORT).show();
        }else if(emailUser.isEmpty()){
            Toast.makeText(this, "Unesite vas email", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Unesite vasu lozinku", Toast.LENGTH_SHORT).show();
        }

    }



}