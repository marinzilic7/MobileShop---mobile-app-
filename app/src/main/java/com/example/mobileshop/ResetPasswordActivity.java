package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        auth = FirebaseAuth.getInstance();
    }

    public void confirmReset(View view){
        EditText email = findViewById(R.id.emailUser);
        String emailUser = email.getText().toString();

        if(emailUser.isEmpty()){
            Toast.makeText(this, "Molimo upišite vaš email", Toast.LENGTH_SHORT).show();
            return;
        }else{
            auth.sendPasswordResetEmail(emailUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ResetPasswordActivity.this, "Poslali smo vam email za resetiranje lozinke", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Greška", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }


}
