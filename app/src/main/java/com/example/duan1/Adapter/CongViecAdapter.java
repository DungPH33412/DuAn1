package com.example.duan1.Adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1.DTO.CongViec;
import com.example.duan1.Dao.CongViecDao;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.Date;


public class CongViecAdapter extends RecyclerView.Adapter<CongViecAdapter.ViewHolder> {
    private  final Context context;
    private final ArrayList<CongViec> list;
    CongViecDao congViecDao;

    public CongViecAdapter(Context context, ArrayList<CongViec> list) {
        this.context = context;
        this.list = list;
        congViecDao = new CongViecDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recy_main,null);
        return new ViewHolder(view);
    }
/////////////////////////////////////////////////////////////
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtten.setText(list.get(position).getTenCV());
        holder.txtcontent.setText(list.get(position).getContent());
        holder.txtstatus.setText(list.get(position).getStatus());
        CongViec cv = list.get(position);
        holder.txtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa:?");
                builder.setTitle("Delete");
                builder.setIcon(R.drawable.warning);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   if (congViecDao.deletedata(cv.getId())){
                       list.clear();
                       list.addAll(congViecDao.getALL());
                       notifyDataSetChanged();
                       Toast.makeText(context, "XOA thanh cong", Toast.LENGTH_SHORT).show();

                   }else {
                       Toast.makeText(context, "Xoa That Bai", Toast.LENGTH_SHORT).show();
                   }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.txtupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
opendialogupdate(cv);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class  ViewHolder extends RecyclerView.ViewHolder {
        TextView txtten,txtcontent,txtstatus,txtupdate,txtxoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten= itemView.findViewById(R.id.txttencongviec);
            txtcontent= itemView.findViewById(R.id.txtcontent);
            txtstatus= itemView.findViewById(R.id.txtstatus);
            txtupdate= itemView.findViewById(R.id.txtupdate);
            txtxoa = itemView.findViewById(R.id.txtdelete);
        }
    }
    public void opendialogupdate(CongViec congViec){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtupdateten= view.findViewById(R.id.edtupdateten);
        EditText edtupdatecontent = view.findViewById(R.id.edtupdatecontent);
        EditText edtupdatestatus = view.findViewById(R.id.edtupdatestatus);
        Button btncn = view.findViewById(R.id.btncn);
        edtupdateten.setText(congViec.getTenCV());
        edtupdatecontent.setText(congViec.getContent());
        edtupdatestatus.setText(congViec.getStatus());
        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                congViec.setTenCV(edtupdateten.getText().toString());
                congViec.setContent(edtupdatecontent.getText().toString());
                congViec.setStatus(edtupdatestatus.getText().toString());
                if (congViecDao.updatedata(congViec)){
                    list.clear();
                    list.addAll(congViecDao.getALL());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context, "That Bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
