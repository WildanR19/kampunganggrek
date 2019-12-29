package com.wildan.kampunganggrek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText email, password, confirmPassword;
    private Button btn_regis;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        registerUser();

        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView(){
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        btn_regis = findViewById(R.id.btn_regis);
        confirmPassword = findViewById(R.id.confirm_password);
        auth = FirebaseAuth.getInstance();
    }

    private void registerUser() {
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menampung imputan user
                String emailUser = email.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();
                String passwordConfirm = confirmPassword.getText().toString().trim();

                //validasi email dan password
                // jika email kosong
                if (emailUser.isEmpty()){
                    email.setError("Email tidak boleh kosong");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
                    email.setError("Email tidak valid");
                } else if (passwordUser.isEmpty()){
                    password.setError("Password tidak boleh kosong");
                } else if (passwordUser.length() < 6){
                    password.setError("Password minimal terdiri dari 6 karakter");
                } else if(passwordConfirm.isEmpty()){
                    confirmPassword.setError("Password tidak boleh kosong");
                } else if (!passwordConfirm.equals(passwordUser)){
                    Toast.makeText(RegisterActivity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                } else {
                    //create user dengan firebase auth
                    auth.createUserWithEmailAndPassword(emailUser,passwordUser)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //jika gagal register do something
                                    if (!task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }else {
                                        //jika sukses akan menuju ke login activity
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    }
                                }
                            });
                }
            }
        });
    }

}
