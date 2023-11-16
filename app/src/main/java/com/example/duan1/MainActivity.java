package com.example.duan1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1.Adapter.CongViecAdapter;
import com.example.duan1.DTO.CongViec;
import com.example.duan1.Dao.CongViecDao;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    RecyclerView recyclerView;
    private NavigationView navigationView;
    CongViecAdapter adapter;
    CongViecDao cvdao;
    private ArrayList<CongViec> list = new ArrayList<>();
   ImageButton btnthem;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản Lý Công Việc");
        drawerLayout = findViewById(R.id.drawerLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_dehaze_24);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
//                R.string.open,R.string.cloes);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
        // nhan iteam
navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.nav_quanly){
        drawerLayout.close();
        }else if (item.getItemId()==R.id.nav_gioithieu){
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
        }else if (item.getItemId()==R.id.nav_caidat){
        caidatdialog();
        }else if (item.getItemId()==R.id.nav_dangxuat){
            dangxuatdialog();
        }

        return false;
    }
});



////////////////////////////////////////////////
        recyclerView = findViewById(R.id.recyclerview);
        cvdao = new CongViecDao(this);
        list= cvdao.getALL();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter= new CongViecAdapter(this,list);
        recyclerView.setAdapter(adapter);
        btnthem= findViewById(R.id.btnThem);
       btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        opendialogThem();

            }
        });

    }
    public void opendialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtten = view.findViewById(R.id.edtaddname);
        EditText edtcontent = view.findViewById(R.id.edtaddcontent);
        EditText edtstatus = view.findViewById(R.id.edtaddstatus);
        Button btnthem = view.findViewById(R.id.btnthem);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtten.getText().toString();
                String content = edtcontent.getText().toString();
                String status = edtstatus.getText().toString();
                int id = edtten.getId();
                CongViec cv = new CongViec(ten,content,status,id);
                if (cvdao.insertdata(cv)){
                    list.clear();
                    list.addAll(cvdao.getALL());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "that bai", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void caidatdialog(){
String work[] ={"Tiếng Việt","English"};
AlertDialog.Builder builder = new AlertDialog.Builder(this);
builder.setIcon(R.drawable.languae);
builder.setTitle("Ngôn Ngữ");
        final String[] select ={"Tiếng Việt"};
        builder.setSingleChoiceItems(work, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                select[0]= work[which];
            }
        });
        ////
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            if (select[0].equals("Tiếng Việt")){
                Toast.makeText(MainActivity.this, "Bạn Đã Chọn Tiếng Việt", Toast.LENGTH_SHORT).show();
                getSupportActionBar().setTitle("Quản Lý Sản Phẩm");
            }else if (select[0].equals("English")){
                Toast.makeText(MainActivity.this, "Bạn Đã Chọn Tiếng ANh", Toast.LENGTH_SHORT).show();
                getSupportActionBar().setTitle(" Product Manager");
            }
            }
        });
        builder.show();
    }
    public void dangxuatdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát");
        builder.setIcon(R.drawable.baseline_cruelty_free_24);
        builder.setMessage("Bạn Có  chắc muốn thoát không");
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            Intent intent = new Intent(MainActivity.this,Login_app.class);
            startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}