package com.example.duan1.Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.duan1.R;


public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void login(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void signup(View view) {
        startActivity(new Intent(this,SignupActivity.class));
    }
}