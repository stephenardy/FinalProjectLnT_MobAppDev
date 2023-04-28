package com.example.lntfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewOnReceiveContentListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private Button btn_Login;
    private TextView tv_goToSignup2;
    private EditText loginEmail, loginPw;

    SharedPreferences sharedPref;
    Intent inten, inten2;
    Toast toast_email, toast_password;
    private FirebaseAuth mAuth;
    DatabaseReference account_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_Login = findViewById(R.id.btn_toHome);
        loginEmail = findViewById(R.id.et_loginEmail);
        loginPw = findViewById(R.id.et_loginPw);
        tv_goToSignup2 = findViewById(R.id.tv_goToSignup2);

        sharedPref = getSharedPreferences("account", MODE_PRIVATE);
        inten = new Intent(getApplicationContext(), MainActivity.class);
        inten2 = new Intent(this, RegisterActivity.class);

        toast_email = Toast.makeText(LoginActivity.this,"Email tidak ditemukan", Toast.LENGTH_LONG);
        toast_password = Toast.makeText(LoginActivity.this,"password salah", Toast.LENGTH_LONG);

        mAuth = FirebaseAuth.getInstance();
        account_db = FirebaseDatabase.getInstance().getReference();

        btn_Login.setOnClickListener(
                view -> {

                    String email = loginEmail.getText().toString().trim();
                    String password = loginPw.getText().toString().trim();

                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                        Toast.makeText(LoginActivity.this, "Email dan password harus di isi", Toast.LENGTH_SHORT).show();
                    } else {
                        // Login dengan cara ambil data dari authentication
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                                LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this, "Log In berhasil!", Toast.LENGTH_SHORT).show();
                                            startActivity(inten);
                                            finish();
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                        );
                          //Sign In with Realtime Database
//                        account_db.child("users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if (snapshot.exists()){
//                                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
//                                        RegisterActivity user = userSnapshot.getValue(RegisterActivity.class);
//                                        String storedEmail = userSnapshot.child("email").getValue(String.class);
//                                        String storedPassword = userSnapshot.child("password").getValue(String.class);
//
//                                        if (storedEmail.equals(email) && storedPassword.equals(password)) {
//
//                                            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
//                                                    LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                                                        @Override
//                                                        public void onComplete(@NonNull Task<AuthResult> task) {
//                                                            if (task.isSuccessful()) {
//                                                                Toast.makeText(LoginActivity.this, "Log In berhasil!", Toast.LENGTH_SHORT).show();
//                                                                startActivity(inten);
//                                                                finish();
//                                                            } else {
//                                                                Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
//                                                            }
//                                                        }
//                                                    }
//                                            );
//
//                                        }
//                                    }
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "Email tidak terdaftar", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
                    }
                    // login dengan ambil data dari SharedPreferences
//                        if (!et_loginEmail.getText().toString().equals(sharedPref.getString("account_email", ""))) {
//                            toast_email.show();
//                        } else if (!et_loginPw.getText().toString().equals(sharedPref.getString("account_password", ""))) {
//                            toast_password.show();
//                        } else if (et_loginEmail.getText().toString().equals(sharedPref.getString("account_email", ""))
//                                && et_loginPw.getText().toString().equals(sharedPref.getString("account_password", ""))) {
//                            inten.putExtra("account_email", sharedPref.getString(("account_email"), ""));
//                            inten.putExtra("account_password", sharedPref.getString("account_password", ""));
//
//                            startActivity(inten);
//                        }
                }
        );

        tv_goToSignup2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(inten2);
                    }
                }
        );
    }

}
