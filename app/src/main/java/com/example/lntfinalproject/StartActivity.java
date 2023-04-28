package com.example.lntfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private TextView tv_startTitle, tv_startSubTitle, tv_atau;
    private Button btn_homeLogin, btn_homeSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tv_startTitle = findViewById(R.id.tv_startTitle);
        tv_startSubTitle = findViewById(R.id.tv_startSubTitle);
        tv_atau = findViewById(R.id.tv_atau);
        btn_homeLogin = findViewById(R.id.btn_homeLogin);
        btn_homeSignUp = findViewById(R.id.btn_homeSignUp);

        Intent inten = new Intent(this, LoginActivity.class);
        Intent inten2 = new Intent(this, RegisterActivity.class);

        btn_homeLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(inten);
                    }
                }
        );

        btn_homeSignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(inten2);
                    }
                }
        );
    }
}