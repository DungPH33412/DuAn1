package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.Dao.UserDao;


public class DangKi extends AppCompatActivity {
    EditText edtusername,edtpassword,edtrepassword;
    Button btndangki;
    UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        edtrepassword = findViewById(R.id.edtrepassword);
        btndangki = findViewById(R.id.btndangki);
        Button btnback = findViewById(R.id.btnback);
        userDao = new UserDao(this);
            btndangki.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String user = edtusername.getText().toString();
                    String pass = edtpassword.getText().toString();
                    String repass = edtrepassword.getText().toString();
                    if (!pass.equals(repass)){
                        Toast.makeText(DangKi.this, "Bạn Cần nhập mật khẩu trùng nhau", Toast.LENGTH_SHORT).show();
                    }else {
                        boolean check = userDao.Dangki(user,pass,repass);
                        if (check){
                            Toast.makeText(DangKi.this, "Đăng Kí thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(DangKi.this, "Đăng Kí thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKi.this,Login_app.class));
            }
        });
    }
    }
