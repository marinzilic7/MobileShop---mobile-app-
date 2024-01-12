package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private  DrawerLayout drawer;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        TextView emailTextView = findViewById(R.id.userEmail);
        TextView nameTextView = findViewById(R.id.userName);


        if(currentUser != null){
            String email = currentUser.getEmail();
            emailTextView.setText(email);
            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid()).child("firstName");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String firstName = snapshot.getValue().toString();
                    nameTextView.setText(firstName);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AccountActivity.this, "Greska", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    public void potvrdiReset(View view){
        EditText novaLozinka = findViewById(R.id.novaLozinka);
        String lozinka = novaLozinka.getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(lozinka.isEmpty()){
            Toast.makeText(this, "Molimo unesite novu lozinku", Toast.LENGTH_SHORT).show();
            return;
        }else{
            user.updatePassword(lozinka).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
                        databaseReference.child("password").setValue(lozinka);
                        Toast.makeText(AccountActivity.this, "Promijenjena lozinka", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AccountActivity.this, "Greska", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void updateProfile(View view){
        openModal();
    }

    private void openModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View DialogView = inflater.inflate(R.layout.modal, null);
        builder.setView(DialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}