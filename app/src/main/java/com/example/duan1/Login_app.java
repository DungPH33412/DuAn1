package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.Dao.UserDao;


public class Login_app extends AppCompatActivity {
    EditText edtusername,edtpassword;
    TextView txtforgot,txtsing_up;
    Button btnlogin;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);
        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        btnlogin = findViewById(R.id.btnlogin);
        txtforgot =findViewById(R.id.txtforgot);
        txtsing_up = findViewById(R.id.txtsignup);
        UserDao userDao = new UserDao(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user  =  edtusername.getText().toString();
                String pass = edtpassword.getText().toString();
                boolean check = userDao.LoginCheck(user,pass);
                if (check){
                    Toast.makeText(Login_app.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login_app.this,MainActivity.class));
                }else {
                    Toast.makeText(Login_app.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtsing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_app.this,DangKi.class));
            }
        });
        //////////////////////////////////
        txtforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogforgot();
            }
        });
    }
    ///////////////////////////////////////dialog/////////////////////////////////
    private void dialogforgot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_forgot,null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        // anhs xa
        EditText edtemail = view.findViewById(R.id.edtemail);
        Button btnmk = view.findViewById(R.id.btnsubmit);
        Button btnhuy = view.findViewById(R.id.btnhuy);
/////////////////////////////////////////////////////////////////////////////////
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail.getText().toString();
                String matkhau = userDao.laymatkhau(email);
                Toast.makeText(Login_app.this, matkhau, Toast.LENGTH_SHORT).show();
            }
        });

    }

    }