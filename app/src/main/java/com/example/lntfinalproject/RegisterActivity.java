package com.example.lntfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_idBimbel, et_email, et_nama, et_password, et_konfirmasiPassword;
    private Button btn_Register;
    private TextView tv_goToLoginTitle, tv_goToLoginLink;
    Intent inten;
    SharedPreferences sharedPref;

    private FirebaseAuth mAuth;
    private DatabaseReference account_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_idBimbel = findViewById(R.id.et_idBimbel);
        et_email = findViewById(R.id.et_email);
        et_nama = findViewById(R.id.et_nama);
        et_password = findViewById(R.id.et_password);
        et_konfirmasiPassword = findViewById(R.id.et_konfirmasiPassword);
        btn_Register = findViewById(R.id.buttonRegister);
        tv_goToLoginTitle = findViewById(R.id.tv_goToLogin);
        tv_goToLoginLink = findViewById(R.id.tv_goToLogin2);

        inten = new Intent(this, LoginActivity.class);
        sharedPref = getSharedPreferences("account", MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        account_db = FirebaseDatabase.getInstance().getReference();

        btn_Register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences.Editor editor = sharedPref.edit();

                        String idBimBel = et_idBimbel.getText().toString();
                        String email = et_email.getText().toString();
                        String nama = et_nama.getText().toString();
                        String password = et_password.getText().toString();
                        String konfirm_password = et_konfirmasiPassword.getText().toString();
                        
                        if (TextUtils.isEmpty(idBimBel)){
                            Toast.makeText(RegisterActivity.this, "ID Bimbel harus di isi", Toast.LENGTH_SHORT).show();
                        } else if (!email.contains("@") || !email.contains(".com")){
                            Toast.makeText(RegisterActivity.this,"Harus terdapat '@' dan '.com'",Toast.LENGTH_SHORT).show();
                        } else if(nama.length() < 5){
                            Toast.makeText(RegisterActivity.this,"nama minimal 5 karakter",Toast.LENGTH_SHORT).show();
                        } else if(TextUtils.isEmpty(password)){
                            Toast.makeText(RegisterActivity.this,"password harus di isi",Toast.LENGTH_SHORT).show();
                        } else if(!konfirm_password.equals(password)){
                            Toast.makeText(RegisterActivity.this,"password yang dikonfirmasi salah",Toast.LENGTH_SHORT).show();
                        } else {
                            // Buat akun di authentication
                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Simpan data di Realtime Database
                                                account_db.child(idBimBel).child("email").setValue(email);
                                                account_db.child(idBimBel).child("nama").setValue(nama);
                                                account_db.child(idBimBel).child("password").setValue(password);

                                                Toast.makeText(RegisterActivity.this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();

                                                // Simpan data di SharedPreferences
                                                editor.putString("account_idBimbel", idBimBel);
                                                editor.putString("account_email", email);
                                                editor.putString("account_nama", nama);
                                                editor.putString("account_password", password);
                                                editor.apply();

                                                startActivity(inten);
                                            } else {
                                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                            );


                        }




                    }
                }
        );

        tv_goToLoginLink.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startActivity(inten);
                    }
                }
        );
    }






}